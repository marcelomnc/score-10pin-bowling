package com.marcelomnc.score10pinbowling.processor;

import com.marcelomnc.score10pinbowling.dto.FrameDTO;
import com.marcelomnc.score10pinbowling.dto.GameDTO;

public class ScoreProcessor implements IGameProcessor {
    @Override
    public void processGame(GameDTO game) {
        this.calculateScore(game, 0, 0);
    }

    private void calculateScore(GameDTO game, int currentFrameIndex, int currentChanceIndex) {
        final FrameDTO currentFrame = game.getFrames().get(currentFrameIndex);

        if (currentFrameIndex > 0) {
            //Current frame score starts with the score of the previous one
            currentFrame.setScore(game.getFrames().get(currentFrameIndex - 1).getScore());
        }

        if (currentFrameIndex == 9) {
            //10th frame (last)
            currentFrame.setScore(currentFrame.getScore()
                    + game.getPlayerChances().get(currentChanceIndex).getKnockedDownPins()
                    + game.getPlayerChances().get(currentChanceIndex + 1).getKnockedDownPins());

            if (currentFrame.getPinFalls().size() == 3) {
                //Extra chance
                currentFrame.setScore(currentFrame.getScore()
                        + game.getPlayerChances().get(currentChanceIndex + 2).getKnockedDownPins());
            }
        } else {
            int nextChanceIndex = currentChanceIndex;
            //Frames 1 to 9
            if (currentFrame.getPinFalls().size() == 1) {
                //It's a strike
                currentFrame.setScore(currentFrame.getScore()
                        + 10
                        + game.getPlayerChances().get(currentChanceIndex + 1).getKnockedDownPins()
                        + game.getPlayerChances().get(currentChanceIndex + 2).getKnockedDownPins());
                nextChanceIndex += 1;

            } else if (currentFrame.getPinFalls().size() == 2
                    && ((currentFrame.getPinFalls().get(0).getKnockedDownPins() + currentFrame.getPinFalls().get(1).getKnockedDownPins()) == 10)) {
                //It's a spare
                currentFrame.setScore(currentFrame.getScore()
                        + 10
                        + game.getPlayerChances().get(currentChanceIndex + 2).getKnockedDownPins());
                nextChanceIndex += 2;
            } else {
                //It's an open
                currentFrame.setScore(currentFrame.getScore()
                        + game.getPlayerChances().get(currentChanceIndex).getKnockedDownPins()
                        + game.getPlayerChances().get(currentChanceIndex + 1).getKnockedDownPins());
                nextChanceIndex += 2;
            }

            //Calculate score for the next frame
            calculateScore(game, currentFrameIndex + 1, nextChanceIndex);
        }
    }
}
