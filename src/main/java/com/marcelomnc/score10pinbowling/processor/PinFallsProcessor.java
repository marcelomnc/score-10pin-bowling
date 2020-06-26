package com.marcelomnc.score10pinbowling.processor;

import com.marcelomnc.score10pinbowling.dto.FrameDTO;
import com.marcelomnc.score10pinbowling.dto.GameDTO;
import com.marcelomnc.score10pinbowling.dto.PinFallDTO;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PinFallsProcessor implements IPinFallsProcessor {
    private static final Logger LOGGER = Logger.getLogger(PinFallsProcessor.class.getName());

    @Override
    public void processPinFalls(Map<String, GameDTO> games) {
        games.forEach((gameOwner, gameDTO) -> {
            gameDTO.getPlayerChanceDTOs().forEach(playerChanceDTO -> {
                if (gameDTO.getFrameDTOS().size() == 0) {
                    //Add first frame to the game
                    this.addNewFrameToGame(gameDTO, null);
                }

                int currentFrameIndex = gameDTO.getFrameDTOS().size() - 1;
                FrameDTO currentFrameDTO = gameDTO.getFrameDTOS().get(currentFrameIndex);
                int currentPinFallIndex = currentFrameDTO.getPinFalls().size();

                if (currentFrameIndex == 9) {
                    //10th frame (last)
                    if (currentFrameDTO.getPinFalls().size() <= 1) {
                        //First and second chance
                        currentFrameDTO.getPinFalls().add(new PinFallDTO(playerChanceDTO.isFoul(), playerChanceDTO.getKnockedDownPins()));
                    } else if (currentFrameDTO.getPinFalls().size() == 2
                            &&
                            (((currentFrameDTO.getPinFalls().get(0).getValue() == 10) || (currentFrameDTO.getPinFalls().get(1).getValue()) == 10)
                                    || ((currentFrameDTO.getPinFalls().get(0).getValue() + currentFrameDTO.getPinFalls().get(1).getValue()) == 10))) {
                        //Third chance only allowed when there is a strike or spare in the first two chances
                        currentFrameDTO.getPinFalls().add(new PinFallDTO(playerChanceDTO.isFoul(), playerChanceDTO.getKnockedDownPins()));
                    } else {
                        //This is an error, because if this is the last frame, 3 chances are the maximum only when there is a strike or spare in the first two chances
                        gameDTO.setValid(false);
                        LOGGER.log(Level.SEVERE, "Player: " + gameDTO.getOwnerName() + ", exceeded chances.");
                        return;
                    }
                } else {
                    //Frames 1 to 9
                    if (playerChanceDTO.getKnockedDownPins() == 10) {
                        //Strike or spare chance
                        if (currentPinFallIndex == 0
                                || (currentFrameDTO.getPinFalls().size() == 1 && currentFrameDTO.getPinFalls().get(0).getValue() == 0)) {
                            //First chance. If second chance, first one must have knocked down pins equal to zero to be a spare
                            currentFrameDTO.getPinFalls().add(new PinFallDTO(playerChanceDTO.isFoul(), playerChanceDTO.getKnockedDownPins()));
                            this.addNewFrameToGame(gameDTO, null);
                        } else if (currentFrameDTO.getPinFalls().size() == 1 && currentFrameDTO.getPinFalls().get(0).getValue() == 10) {
                            //The chance data is meant for the next frame, because the first chance was a strike
                            this.addNewFrameToGame(gameDTO, new PinFallDTO(playerChanceDTO.isFoul(), playerChanceDTO.getKnockedDownPins()));
                        } else {
                            //This is an error, because if this is the second chance, sum must be NOT greater than 10
                            gameDTO.setValid(false);
                            LOGGER.log(Level.SEVERE, "Player: " + gameDTO.getOwnerName() + ", frame: " + (currentFrameIndex + 1) + " exceeded pinfall sum.");
                            return;
                        }
                    } else {
                        if (currentPinFallIndex == 0) {
                            //First chance.
                            currentFrameDTO.getPinFalls().add(new PinFallDTO(playerChanceDTO.isFoul(), playerChanceDTO.getKnockedDownPins()));
                        } else if (currentFrameDTO.getPinFalls().size() == 1
                                && (currentFrameDTO.getPinFalls().get(0).getValue() + playerChanceDTO.getKnockedDownPins()) <= 10) {
                            //Second chance, sum for the first and second must be NOT greater than 10
                            currentFrameDTO.getPinFalls().add(new PinFallDTO(playerChanceDTO.isFoul(), playerChanceDTO.getKnockedDownPins()));
                            this.addNewFrameToGame(gameDTO, null);
                        } else if (currentFrameDTO.getPinFalls().size() == 2) {
                            //The chance data is meant for the next frame
                            this.addNewFrameToGame(gameDTO, new PinFallDTO(playerChanceDTO.isFoul(), playerChanceDTO.getKnockedDownPins()));
                        } else {
                            //This is an error, because if this is the second chance, sum will be more than 10
                            gameDTO.setValid(false);
                            LOGGER.log(Level.SEVERE, "Player: " + gameDTO.getOwnerName() + ", frame: " + (currentFrameIndex + 1) + " exceeded pinfall sum.");
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
