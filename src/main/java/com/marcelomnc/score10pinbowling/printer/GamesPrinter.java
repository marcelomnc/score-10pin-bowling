package com.marcelomnc.score10pinbowling.printer;

import com.marcelomnc.score10pinbowling.common.Constants;
import com.marcelomnc.score10pinbowling.dto.FrameDTO;
import com.marcelomnc.score10pinbowling.dto.GameDTO;

import java.util.Map;

public class GamesPrinter {
    public void printGames(Map<String, GameDTO> games) {
        //Printing frames header
        System.out.println("\n-----------------------------------------------------------------\n");
        System.out.print(Constants.PRINT__FRAME_LABEL);
        for (int i = 1; i <= 9; i++) {
            System.out.print(Constants.PRINT__FRAME_SEPARATOR + i);
        }
        System.out.println(Constants.PRINT__FRAME_SEPARATOR + 10);

        games.forEach((gameOwner, gameDTO) -> {
            System.out.println(gameDTO.getOwnerName());
            System.out.print(Constants.PRINT__PINFALLS_LABEL + Constants.PRINT__PINFALLS_VALUE_SEPARATOR);

            //Printing pinfalls
            for (int i = 0; i < gameDTO.getFrameDTOS().size(); i++) {
                FrameDTO frame = gameDTO.getFrameDTOS().get(i);
                String toPrint1, toPrint2, toPrint3 = "";
                if (i == 9) {
                    //10th frame (last)
                    if (frame.getPinFalls().get(0).isFoul()) {
                        toPrint1 = Constants.PRINT__PINFALLS_FOUL_INDICATOR + Constants.PRINT__PINFALLS_VALUE_SEPARATOR;
                    } else if (frame.getPinFalls().get(0).getValue() == 10) {
                        toPrint1 = Constants.PRINT__PINFALLS_STRIKE_INDICATOR + Constants.PRINT__PINFALLS_VALUE_SEPARATOR;
                    } else {
                        toPrint1 = frame.getPinFalls().get(0).getValue() + Constants.PRINT__PINFALLS_VALUE_SEPARATOR;
                    }

                    if (frame.getPinFalls().get(1).isFoul()) {
                        toPrint2 = Constants.PRINT__PINFALLS_FOUL_INDICATOR + Constants.PRINT__PINFALLS_VALUE_SEPARATOR;
                    } else if (frame.getPinFalls().get(1).getValue() == 10) {
                        toPrint2 = Constants.PRINT__PINFALLS_STRIKE_INDICATOR + Constants.PRINT__PINFALLS_VALUE_SEPARATOR;
                    } else if ((frame.getPinFalls().get(0).getValue() + frame.getPinFalls().get(1).getValue()) == 10) {
                        toPrint2 = Constants.PRINT__PINFALLS_SPARE_INDICATOR + Constants.PRINT__PINFALLS_VALUE_SEPARATOR;
                    } else {
                        toPrint2 = frame.getPinFalls().get(1).getValue() + Constants.PRINT__PINFALLS_VALUE_SEPARATOR;
                    }

                    if (frame.getPinFalls().size() == 3) {
                        if (frame.getPinFalls().get(2).isFoul()) {
                            toPrint3 = Constants.PRINT__PINFALLS_FOUL_INDICATOR + Constants.PRINT__PINFALLS_VALUE_SEPARATOR;
                        } else if (frame.getPinFalls().get(2).getValue() == 10) {
                            toPrint3 = Constants.PRINT__PINFALLS_STRIKE_INDICATOR + Constants.PRINT__PINFALLS_VALUE_SEPARATOR;
                        } else {
                            toPrint3 = frame.getPinFalls().get(2).getValue() + Constants.PRINT__PINFALLS_VALUE_SEPARATOR;
                        }
                    }
                } else {
                    //Frames 1 to 9
                    if (frame.getPinFalls().size() == 1) {
                        toPrint1 = Constants.PRINT__PINFALLS_CHANCE_SKIPPED_INDICATOR + Constants.PRINT__PINFALLS_VALUE_SEPARATOR;
                        toPrint2 = Constants.PRINT__PINFALLS_STRIKE_INDICATOR;
                    } else {
                        toPrint1 = frame.getPinFalls().get(0).isFoul() ? Constants.PRINT__PINFALLS_FOUL_INDICATOR : String.valueOf(frame.getPinFalls().get(0).getValue());
                        toPrint1 += Constants.PRINT__PINFALLS_VALUE_SEPARATOR;

                        toPrint2 = frame.getPinFalls().get(1).isFoul() ? Constants.PRINT__PINFALLS_FOUL_INDICATOR : String.valueOf(frame.getPinFalls().get(1).getValue());
                        if ((frame.getPinFalls().get(0).getValue() + frame.getPinFalls().get(1).getValue()) == 10) {
                            toPrint2 = Constants.PRINT__PINFALLS_SPARE_INDICATOR;
                        }
                    }
                }

                if (i == 9) {
                    //10th frame (last)
                    System.out.print(toPrint1);
                    System.out.print(toPrint2);
                    System.out.println(toPrint3);
                } else {
                    //Frames 1 to 9
                    System.out.print(toPrint1);
                    System.out.print(toPrint2 + Constants.PRINT__PINFALLS_VALUE_SEPARATOR);
                }
            }

            //Printing scores
            System.out.print(Constants.PRINT__SCORE_LABEL + Constants.PRINT__SCORE_SEPARATOR);
            for (int i = 0; i < gameDTO.getFrameDTOS().size(); i++) {
                FrameDTO frameDTO = gameDTO.getFrameDTOS().get(i);

                if (i == 9) {
                    //10th frame (last)
                    System.out.println(frameDTO.getScore());
                } else {
                    //Frames 1 to 9
                    System.out.print(frameDTO.getScore() + Constants.PRINT__SCORE_SEPARATOR);
                }
            }
        });

        System.out.println("\n-----------------------------------------------------------------\n");
    }
}
