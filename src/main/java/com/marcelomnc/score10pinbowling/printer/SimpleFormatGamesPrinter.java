package com.marcelomnc.score10pinbowling.printer;

import com.marcelomnc.score10pinbowling.common.Constants;
import com.marcelomnc.score10pinbowling.dto.FrameDTO;
import com.marcelomnc.score10pinbowling.dto.GameDTO;

import java.util.Map;

public class SimpleFormatGamesPrinter implements IGamesPrinter {

    @Override
    public void printGames(Map<String, GameDTO> games) {
        this.printFramesHeader();

        games.forEach((gameOwner, gameDTO) -> {
            //Only print valid games
            if (gameDTO.isValid()) {
                //Printing player name
                System.out.println(gameDTO.getPlayerName());

                //Printing pinFalls
                StringBuilder sb = new StringBuilder();
                sb = new StringBuilder();
                sb.append(Constants.PRINT__PINFALLS_LABEL);
                sb.append(Constants.PRINT__FIELD_FILLER);
                sb.append(Constants.PRINT__FIELD_SEPARATOR);

                for (int i = 0; i < gameDTO.getFrameDTOS().size(); i++) {
                    FrameDTO frame = gameDTO.getFrameDTOS().get(i);
                    if (i == 9) {
                        //10th frame (last)
                        if (frame.getPinFalls().get(0).isFoul()) {
                            sb.append(Constants.PRINT__PINFALLS_FOUL_INDICATOR);
                        } else if (frame.getPinFalls().get(0).getValue() == 10) {
                            sb.append(Constants.PRINT__PINFALLS_STRIKE_INDICATOR);
                        } else {
                            sb.append(frame.getPinFalls().get(0).getValue());
                        }
                        sb.append(Constants.PRINT__FIELD_FILLER);
                        sb.append(Constants.PRINT__FIELD_SEPARATOR);

                        if (frame.getPinFalls().get(1).isFoul()) {
                            sb.append(Constants.PRINT__PINFALLS_FOUL_INDICATOR);
                        } else if (frame.getPinFalls().get(1).getValue() == 10) {
                            sb.append(Constants.PRINT__PINFALLS_STRIKE_INDICATOR);
                        } else if ((frame.getPinFalls().get(0).getValue() + frame.getPinFalls().get(1).getValue()) == 10) {
                            sb.append(Constants.PRINT__PINFALLS_SPARE_INDICATOR);
                        } else {
                            sb.append(frame.getPinFalls().get(1).getValue());
                        }

                        if (frame.getPinFalls().size() == 3) {
                            sb.append(Constants.PRINT__FIELD_FILLER);
                            sb.append(Constants.PRINT__FIELD_SEPARATOR);

                            if (frame.getPinFalls().get(2).isFoul()) {
                                sb.append(Constants.PRINT__PINFALLS_FOUL_INDICATOR);
                            } else if (frame.getPinFalls().get(2).getValue() == 10) {
                                sb.append(Constants.PRINT__PINFALLS_STRIKE_INDICATOR);
                            } else {
                                sb.append(frame.getPinFalls().get(2).getValue());
                            }
                        }
                    } else {
                        //Frames 1 to 9
                        if (frame.getPinFalls().size() == 1) {
                            sb.append(Constants.PRINT__FIELD_FILLER);
                            sb.append(Constants.PRINT__FIELD_SEPARATOR);
                            sb.append(Constants.PRINT__PINFALLS_STRIKE_INDICATOR);
                            sb.append(Constants.PRINT__FIELD_FILLER);
                            sb.append(Constants.PRINT__FIELD_SEPARATOR);
                        } else {
                            sb.append(frame.getPinFalls().get(0).isFoul() ? Constants.PRINT__PINFALLS_FOUL_INDICATOR : String.valueOf(frame.getPinFalls().get(0).getValue()));
                            sb.append(Constants.PRINT__FIELD_FILLER);
                            sb.append(Constants.PRINT__FIELD_SEPARATOR);

                            if ((frame.getPinFalls().get(0).getValue() + frame.getPinFalls().get(1).getValue()) == 10) {
                                sb.append(Constants.PRINT__PINFALLS_SPARE_INDICATOR);
                            } else {
                                sb.append(frame.getPinFalls().get(1).isFoul() ? Constants.PRINT__PINFALLS_FOUL_INDICATOR : String.valueOf(frame.getPinFalls().get(1).getValue()));
                            }
                            sb.append(Constants.PRINT__FIELD_FILLER);
                            sb.append(Constants.PRINT__FIELD_SEPARATOR);
                        }
                    }
                }
                System.out.println(sb.toString());

                //Printing scores
                sb = new StringBuilder();
                sb.append(Constants.PRINT__SCORE_LABEL);
                sb.append(Constants.PRINT__FIELD_FILLER);
                sb.append(Constants.PRINT__FIELD_SEPARATOR);
                sb.append(Constants.PRINT__FIELD_FILLER);
                sb.append(Constants.PRINT__FIELD_SEPARATOR);

                for (int i = 0; i < gameDTO.getFrameDTOS().size(); i++) {
                    FrameDTO frameDTO = gameDTO.getFrameDTOS().get(i);
                    String strScore = String.valueOf(frameDTO.getScore());

                    if (i == 9) {
                        //10th frame (last)
                        sb.append(strScore);
                    } else {
                        sb.append(strScore);
                        sb.append(Constants.PRINT__FIELD_FILLER);
                        sb.append(Constants.PRINT__FIELD_SEPARATOR);
                        sb.append(Constants.PRINT__FIELD_FILLER);
                        sb.append(Constants.PRINT__FIELD_SEPARATOR);
                    }
                }
                System.out.println(sb.toString());
            }
        });
    }

    private void printFramesHeader() {
        //Printing frames header
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.PRINT__FRAME_LABEL);
        sb.append(Constants.PRINT__FIELD_FILLER);
        sb.append(Constants.PRINT__FIELD_SEPARATOR);
        sb.append(Constants.PRINT__FIELD_FILLER);
        sb.append(Constants.PRINT__FIELD_SEPARATOR);
        for (int i = 1; i <= 9; i++) {
            sb.append(i);
            sb.append(Constants.PRINT__FIELD_FILLER);
            sb.append(Constants.PRINT__FIELD_SEPARATOR);
            sb.append(Constants.PRINT__FIELD_FILLER);
            sb.append(Constants.PRINT__FIELD_SEPARATOR);
        }
        sb.append(10);
        System.out.println(sb.toString());
    }
}
