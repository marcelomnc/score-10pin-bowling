package com.marcelomnc.score10pinbowling.processor;

import com.marcelomnc.score10pinbowling.dto.FrameDTO;
import com.marcelomnc.score10pinbowling.dto.GameDTO;

import java.util.Map;

public class ScoresProcessor {
    public void processScores(Map<String, GameDTO> games) {
        games.forEach((gameOwner, gameDTO) -> {
            this.calculateScore(gameDTO, 0, 0);
        });
    }

    private void calculateScore(GameDTO gameDTO, int frameIndex, int chanceIndex) {
        FrameDTO frameDTO = gameDTO.getFrameDTOS().get(frameIndex);

        if (frameIndex > 0) {
            //Frame score starts with the previous one
            frameDTO.setScore(gameDTO.getFrameDTOS().get(frameIndex - 1).getScore());
        }

        if (frameIndex == 9) {
            //10th frame (last)
            frameDTO.setScore(frameDTO.getScore()
                    + gameDTO.getPlayerChanceDTOs().get(chanceIndex).getKnockedDownPins()
                    + gameDTO.getPlayerChanceDTOs().get(chanceIndex + 1).getKnockedDownPins());

            if (frameDTO.getPinFalls().size() == 3) {
                //Extra chance
                frameDTO.setScore(frameDTO.getScore()
                        + gameDTO.getPlayerChanceDTOs().get(chanceIndex + 2).getKnockedDownPins());
            }
        } else {
            int nextChanceIndex = chanceIndex;
            //Frames 1 to 9
            if (frameDTO.getPinFalls().size() == 1) {
                //Its a strike
                frameDTO.setScore(frameDTO.getScore()
                        + 10
                        + gameDTO.getPlayerChanceDTOs().get(chanceIndex + 1).getKnockedDownPins()
                        + gameDTO.getPlayerChanceDTOs().get(chanceIndex + 2).getKnockedDownPins());
                nextChanceIndex += 1;

            } else if (frameDTO.getPinFalls().size() == 2
                    && ((frameDTO.getPinFalls().get(0).getValue() + frameDTO.getPinFalls().get(1).getValue()) == 10)) {
                //Its a spare
                frameDTO.setScore(frameDTO.getScore()
                        + 10
                        + gameDTO.getPlayerChanceDTOs().get(chanceIndex + 2).getKnockedDownPins());
                nextChanceIndex += 2;
            } else {
                //Its an open
                frameDTO.setScore(frameDTO.getScore()
                        + gameDTO.getPlayerChanceDTOs().get(chanceIndex).getKnockedDownPins()
                        + gameDTO.getPlayerChanceDTOs().get(chanceIndex + 1).getKnockedDownPins());
                nextChanceIndex += 2;
            }

            //Calc next frame
            calculateScore(gameDTO, frameIndex + 1, nextChanceIndex);
        }
    }
}
