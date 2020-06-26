package com.marcelomnc.score10pinbowling.processor;

import com.marcelomnc.score10pinbowling.dto.FrameDTO;
import com.marcelomnc.score10pinbowling.dto.GameDTO;
import com.marcelomnc.score10pinbowling.dto.PinFallDTO;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PinFallsProcessor {

    private static final Logger LOGGER = Logger.getLogger(PinFallsProcessor.class.getName());

    public void processPinFalls(Map<String, GameDTO> games) {
        games.forEach((gameOwner, gameDTO) -> {
            gameDTO.getPlayerChanceDTOs().forEach(playerChanceDTO -> {
                if (gameDTO.getFrameDTOS().size() == 0) {
                    this.addNewFrameToGame(gameDTO, null);
                }

                int actualFrameIndex = gameDTO.getFrameDTOS().size() - 1;
                FrameDTO actualFrameDTO = gameDTO.getFrameDTOS().get(actualFrameIndex);
                int actualPinFallIndex = actualFrameDTO.getPinFalls().size();

                if (playerChanceDTO.getKnockedDownPins() == 10) {
                    if (actualFrameIndex == 9) {
                        //Its the last frame
                        if (actualFrameDTO.getPinFalls().size() <= 2) {
                            actualFrameDTO.getPinFalls().add(new PinFallDTO(playerChanceDTO.isFoul(), playerChanceDTO.getKnockedDownPins()));
                        } else {
                            //This is an error, because if this is the last frame, 3 chances are the maximum
                            LOGGER.log(Level.SEVERE, "Player: " + gameDTO.getOwnerName() + ", exceeded chances.");
                            return;
                        }
                    } else {
                        if (actualPinFallIndex == 0
                                || (actualFrameDTO.getPinFalls().size() == 1 && actualFrameDTO.getPinFalls().get(0).getValue() == 0)) {
                            //First chance of the frame, or if second chance, first one must have knocked down pins equal to zero
                            actualFrameDTO.getPinFalls().add(new PinFallDTO(playerChanceDTO.isFoul(), playerChanceDTO.getKnockedDownPins()));
                            this.addNewFrameToGame(gameDTO, null);
                        } else if (actualFrameDTO.getPinFalls().size() == 1 && actualFrameDTO.getPinFalls().get(0).getValue() == 10) {
                            //The chance data is meant for the next frame, because the first chance was a strike
                            this.addNewFrameToGame(gameDTO, new PinFallDTO(playerChanceDTO.isFoul(), playerChanceDTO.getKnockedDownPins()));
                        } else {
                            //This is an error, because if this is the second chance, sum must be NOT greater than 10
                            LOGGER.log(Level.SEVERE, "Player: " + gameDTO.getOwnerName() + ", frame: " + (actualFrameIndex + 1) + " exceeded pinfall sum.");
                            return;
                        }
                    }
                } else {
                    if (actualFrameIndex == 9) {
                        //TODO: Its the last frame

                    } else {
                        if (actualPinFallIndex == 0) {
                            //First chance of the frame
                            actualFrameDTO.getPinFalls().add(new PinFallDTO(playerChanceDTO.isFoul(), playerChanceDTO.getKnockedDownPins()));
                        } else if (actualFrameDTO.getPinFalls().size() == 1
                                && (actualFrameDTO.getPinFalls().get(0).getValue() + playerChanceDTO.getKnockedDownPins()) <= 10) {
                            //Second chance of the frame, sum for the first and second must be NOT greater than 10
                            actualFrameDTO.getPinFalls().add(new PinFallDTO(playerChanceDTO.isFoul(), playerChanceDTO.getKnockedDownPins()));
                            this.addNewFrameToGame(gameDTO, null);
                        } else if (actualFrameDTO.getPinFalls().size() == 2) {
                            //The scoreLine is meant for the next frame
                            this.addNewFrameToGame(gameDTO, new PinFallDTO(playerChanceDTO.isFoul(), playerChanceDTO.getKnockedDownPins()));
                        } else {
                            //This is an error, because if this is the second attempt, sum will be more than 10
                            LOGGER.log(Level.SEVERE, "Player: " + gameDTO.getOwnerName() + ", frame: " + (actualFrameIndex + 1) + " exceeded pinfall sum.");
                            return;
                        }
                    }
                }
            });
        });
    }

    private void addNewFrameToGame(GameDTO gameDTO, PinFallDTO pinFallDTO) {
        FrameDTO nextFrameDTO = new FrameDTO();
        if (pinFallDTO != null) {
            nextFrameDTO.getPinFalls().add(pinFallDTO);
        }
        gameDTO.getFrameDTOS().add(nextFrameDTO);
    }
}
