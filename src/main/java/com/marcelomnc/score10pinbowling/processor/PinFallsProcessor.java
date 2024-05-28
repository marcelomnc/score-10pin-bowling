package com.marcelomnc.score10pinbowling.processor;

import com.marcelomnc.score10pinbowling.common.Constants;
import com.marcelomnc.score10pinbowling.dto.FrameDTO;
import com.marcelomnc.score10pinbowling.dto.GameDTO;
import com.marcelomnc.score10pinbowling.dto.PinFallDTO;
import com.marcelomnc.score10pinbowling.dto.PlayerChanceDTO;

public class PinFallsProcessor implements IGameProcessor {
    @Override
    public void processGame(GameDTO game) {
        this.addNewFrameToGame(game, null);
        
        game.getPlayerChances().forEach(playerChanceDTO -> {
            if (game.isValid()) {
                int currentFrameIndex = game.getFrames().size() - 1;
                final FrameDTO currentFrame = game.getFrames().get(currentFrameIndex);
                int currentPinFallIndex = currentFrame.getPinFalls().size();

                if (currentFrameIndex < 9) {
                    this.processRegularFrame(game, playerChanceDTO, currentFrameIndex, currentFrame, currentPinFallIndex);
                } else {
                    //Last Frame (10th)
                    this.processLastFrame(game, playerChanceDTO, currentFrameIndex, currentFrame, currentPinFallIndex);
                }
            }
        });

        //If the game has not been invalidated yet, we must verify that the data in the input file was enough to build its information completely
        if (game.isValid()
                && !this.isGameComplete(game)) {
            //There is not enough data in the input file to build the entire game
            this.invalidateGame(game, Constants.PCFL_VALIDATOR__NOT_ENOUGH_DATA_MESSAGE);
        }
    }

    private void processRegularFrame(GameDTO game, PlayerChanceDTO playerChance, int currentFrameIndex, FrameDTO currentFrame, int currentPinFallIndex) {
        if (playerChance.getKnockedDownPins() == 10) {
            //Strike or spare chance
            if (currentPinFallIndex == 0
                    || (currentPinFallIndex == 1 && currentFrame.getPinFalls().get(0).getKnockedDownPins() == 0)) {
                //When first chance, it's a strike
                //If second chance, first one must have knocked down zero pins to be a spare
                currentFrame.getPinFalls().add(new PinFallDTO(playerChance.isFoul(), playerChance.getKnockedDownPins()));
                this.addNewFrameToGame(game, null);
            } else if (currentPinFallIndex == 1
                    && currentFrame.getPinFalls().get(0).getKnockedDownPins() == 10) {
                //Second chance, this chance data is meant to be in the next frame, because the first chance was a strike
                this.addNewFrameToGame(game, new PinFallDTO(playerChance.isFoul(), playerChance.getKnockedDownPins()));
            } else {
                //This is an error, because if this is the second chance, pin fall sum must be NOT greater than 10
                this.invalidateGame(game,
                        String.format(
                                Constants.PCFL_VALIDATOR__FRAME_EXCEEDS_MAX_SUM_MESSAGE_PATTERN,
                                (currentFrameIndex + 1),
                                (playerChance.getKnockedDownPins() + currentFrame.getPinFalls().get(currentPinFallIndex - 1).getKnockedDownPins())));
            }
        } else {
            if (currentPinFallIndex == 0) {
                //First chance.
                currentFrame.getPinFalls().add(new PinFallDTO(playerChance.isFoul(), playerChance.getKnockedDownPins()));
            } else if (currentPinFallIndex == 1
                    && (currentFrame.getPinFalls().get(0).getKnockedDownPins() + playerChance.getKnockedDownPins()) <= 10) {
                //Second chance, sum for the first and second chances must be NOT greater than 10
                currentFrame.getPinFalls().add(new PinFallDTO(playerChance.isFoul(), playerChance.getKnockedDownPins()));
                this.addNewFrameToGame(game, null);
            } else {
                //This is an error, because if this is the second chance, pin fall sum must be NOT greater than 10
                this.invalidateGame(game,
                        String.format(
                                Constants.PCFL_VALIDATOR__FRAME_EXCEEDS_MAX_SUM_MESSAGE_PATTERN,
                                (currentFrameIndex + 1),
                                (playerChance.getKnockedDownPins() + currentFrame.getPinFalls().get(currentPinFallIndex - 1).getKnockedDownPins())));
            }
        }
    }

    private void processLastFrame(GameDTO game, PlayerChanceDTO playerChance, int currentFrameIndex, FrameDTO currentFrame, int currentPinFallIndex) {
        if (currentPinFallIndex == 0) {
            //First chance
            currentFrame.getPinFalls().add(new PinFallDTO(playerChance.isFoul(), playerChance.getKnockedDownPins()));
        } else if (currentPinFallIndex == 1
                &&
                ((currentFrame.getPinFalls().get(0).getKnockedDownPins() == 10)
                        || (currentFrame.getPinFalls().get(0).getKnockedDownPins() + playerChance.getKnockedDownPins()) <= 10)) {
            //Second chance
            currentFrame.getPinFalls().add(new PinFallDTO(playerChance.isFoul(), playerChance.getKnockedDownPins()));
        } else if (currentPinFallIndex == 2
                &&
                ((currentFrame.getPinFalls().get(0).getKnockedDownPins() == 10)
                        || (currentFrame.getPinFalls().get(0).getKnockedDownPins() + currentFrame.getPinFalls().get(1).getKnockedDownPins()) == 10)) {
            //Third (extra) chance only allowed when there is a strike on the first chance, or a spare in the first two chances
            currentFrame.getPinFalls().add(new PinFallDTO(playerChance.isFoul(), playerChance.getKnockedDownPins()));
        } else {
            this.invalidateGame(game, this.getInvalidationMessage(playerChance, currentFrame, currentFrameIndex, currentPinFallIndex));
        }
    }

    private void invalidateGame(GameDTO game, String invalidationMessage) {
        game.setValid(false);
        game.setInvalidationMessage(invalidationMessage);
    }

    private String getInvalidationMessage(PlayerChanceDTO playerChance, FrameDTO currentFrame, int currentFrameIndex, int currentPinFallIndex) {
        String message;
        if (currentPinFallIndex == 1) {
            //This is an error, because if this is the second chance, pin fall sum must be NOT greater than 10
            message = String.format(
                    Constants.PCFL_VALIDATOR__FRAME_EXCEEDS_MAX_SUM_MESSAGE_PATTERN,
                    (currentFrameIndex + 1),
                    (playerChance.getKnockedDownPins() + currentFrame.getPinFalls().get(0).getKnockedDownPins()));
        } else {
            //Third (extra) chance only allowed when there is a strike in the first chance, or a spare in the first two chances
            message = String.format(Constants.PCFL_VALIDATOR__MAX_CHANCES_EXCEEDED_MESSAGE_PATTERN, (currentFrameIndex + 1));
        }
        return message;
    }

    private void addNewFrameToGame(GameDTO gameDTO, PinFallDTO pinFallDTO) {
        final FrameDTO nextFrameDTO = new FrameDTO();
        if (pinFallDTO != null) {
            nextFrameDTO.getPinFalls().add(pinFallDTO);
        }
        gameDTO.getFrames().add(nextFrameDTO);
    }

    private boolean isGameComplete(GameDTO game) {
        return game.getFrames().size() == 10
                && isLastFrameComplete(game.getFrames().get(9));
    }

    private boolean isLastFrameComplete(FrameDTO lastFrame) {
        if (lastFrame.getPinFalls().isEmpty()
                || lastFrame.getPinFalls().size() == 1) {
            //Last frame must have at least 2 pin falls (throws)
            return false;
        }

        if (lastFrame.getPinFalls().size() == 2
                && ((lastFrame.getPinFalls().get(0).getKnockedDownPins() == 10)
                || (lastFrame.getPinFalls().get(0).getKnockedDownPins() + lastFrame.getPinFalls().get(1).getKnockedDownPins() == 10))) {
            //Last frame must have 3 pin falls (throws) if the first throw was a strike or if the sum of the first two is a spare
            return false;
        }

        return true;
    }
}
