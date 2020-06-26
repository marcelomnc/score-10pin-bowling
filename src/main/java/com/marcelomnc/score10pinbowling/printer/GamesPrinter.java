package com.marcelomnc.score10pinbowling.printer;

import com.marcelomnc.score10pinbowling.common.Constants;
import com.marcelomnc.score10pinbowling.common.StringUtils;
import com.marcelomnc.score10pinbowling.dto.FrameDTO;
import com.marcelomnc.score10pinbowling.dto.GameDTO;

import java.util.Map;

public class GamesPrinter implements IGamesPrinter {

    @Override
    public void printGames(Map<String, GameDTO> games) {
        //Printing frames header
        System.out.println("\n" + "-----------------------------------" + Constants.PRINT__GAMES_LABEL + "------------------------------------");
        System.out.print(StringUtils.justifyLeft(Constants.PRINT__FRAME_LABEL, Constants.PRINT__FIRST_COLUMN_MAX_CHARS) + Constants.PRINT__FRAME_SEPARATOR);

        for (int i = 1; i <= 9; i++) {
            System.out.print(StringUtils.center(String.valueOf(i), Constants.PRINT__FRAME_MAX_CHARS) + Constants.PRINT__FRAME_SEPARATOR);
        }
        System.out.println(StringUtils.center(String.valueOf(10), Constants.PRINT__FRAME_MAX_CHARS) + Constants.PRINT__FRAME_SEPARATOR);

        games.forEach((gameOwner, gameDTO) -> {
            if (gameDTO.isValid()) {
                //Only print valid games

                System.out.println(gameDTO.getOwnerName());
                System.out.print(StringUtils.justifyLeft(Constants.PRINT__PINFALLS_LABEL, Constants.PRINT__FIRST_COLUMN_MAX_CHARS) + Constants.PRINT__FRAME_SEPARATOR);

                //Printing pinfalls
                for (int i = 0; i < gameDTO.getFrameDTOS().size(); i++) {
                    FrameDTO frame = gameDTO.getFrameDTOS().get(i);
                    String toPrint1, toPrint2, toPrint3 = "";
                    if (i == 9) {
                        //10th frame (last)
                        if (frame.getPinFalls().get(0).isFoul()) {
                            toPrint1 = Constants.PRINT__PINFALLS_FOUL_INDICATOR;
                        } else if (frame.getPinFalls().get(0).getValue() == 10) {
                            toPrint1 = Constants.PRINT__PINFALLS_STRIKE_INDICATOR;
                        } else {
                            toPrint1 = String.valueOf(frame.getPinFalls().get(0).getValue());
                        }

                        if (frame.getPinFalls().get(1).isFoul()) {
                            toPrint2 = Constants.PRINT__PINFALLS_FOUL_INDICATOR;
                        } else if (frame.getPinFalls().get(1).getValue() == 10) {
                            toPrint2 = Constants.PRINT__PINFALLS_STRIKE_INDICATOR;
                        } else if ((frame.getPinFalls().get(0).getValue() + frame.getPinFalls().get(1).getValue()) == 10) {
                            toPrint2 = Constants.PRINT__PINFALLS_SPARE_INDICATOR;
                        } else {
                            toPrint2 = String.valueOf(frame.getPinFalls().get(1).getValue());
                        }

                        if (frame.getPinFalls().size() == 3) {
                            if (frame.getPinFalls().get(2).isFoul()) {
                                toPrint3 = Constants.PRINT__PINFALLS_FOUL_INDICATOR;
                            } else if (frame.getPinFalls().get(2).getValue() == 10) {
                                toPrint3 = Constants.PRINT__PINFALLS_STRIKE_INDICATOR;
                            } else {
                                toPrint3 = String.valueOf(frame.getPinFalls().get(2).getValue());
                            }
                        }
                    } else {
                        //Frames 1 to 9
                        if (frame.getPinFalls().size() == 1) {
                            toPrint1 = Constants.PRINT__PINFALLS_CHANCE_SKIPPED_INDICATOR;
                            toPrint2 = Constants.PRINT__PINFALLS_STRIKE_INDICATOR;
                        } else {
                            toPrint1 = frame.getPinFalls().get(0).isFoul() ? Constants.PRINT__PINFALLS_FOUL_INDICATOR : String.valueOf(frame.getPinFalls().get(0).getValue());
                            toPrint2 = frame.getPinFalls().get(1).isFoul() ? Constants.PRINT__PINFALLS_FOUL_INDICATOR : String.valueOf(frame.getPinFalls().get(1).getValue());
                            if ((frame.getPinFalls().get(0).getValue() + frame.getPinFalls().get(1).getValue()) == 10) {
                                toPrint2 = Constants.PRINT__PINFALLS_SPARE_INDICATOR;
                            }
                        }
                    }

                    String frameStr = toPrint1 + " " + toPrint2 + (!toPrint3.equals("") ? " " + toPrint3 : "");
                    if (i == 9) {
                        //10th frame (last)
                        System.out.println(StringUtils.center(frameStr, Constants.PRINT__FRAME_MAX_CHARS) + Constants.PRINT__FRAME_SEPARATOR);
                    } else {
                        //Frames 1 to 9
                        System.out.print(StringUtils.center(frameStr, Constants.PRINT__FRAME_MAX_CHARS) + Constants.PRINT__FRAME_SEPARATOR);
                    }
                }

                //Printing scores
                System.out.print(StringUtils.justifyLeft(Constants.PRINT__SCORE_LABEL, Constants.PRINT__FIRST_COLUMN_MAX_CHARS) + Constants.PRINT__FRAME_SEPARATOR);

                for (int i = 0; i < gameDTO.getFrameDTOS().size(); i++) {
                    FrameDTO frameDTO = gameDTO.getFrameDTOS().get(i);

                    if (i == 9) {
                        //10th frame (last)
                        System.out.println(StringUtils.center(String.valueOf(frameDTO.getScore()), Constants.PRINT__FRAME_MAX_CHARS) + Constants.PRINT__FRAME_SEPARATOR);
                    } else {
                        //Frames 1 to 9
                        System.out.print(StringUtils.center(String.valueOf(frameDTO.getScore()), Constants.PRINT__FRAME_MAX_CHARS) + Constants.PRINT__FRAME_SEPARATOR);
                    }
                }
            }
        });

        System.out.println("----------------------------------------------------------------------------" + "\n");
    }
}
