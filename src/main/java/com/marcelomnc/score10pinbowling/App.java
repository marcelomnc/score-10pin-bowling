package com.marcelomnc.score10pinbowling;

import com.marcelomnc.score10pinbowling.common.Constants;
import com.marcelomnc.score10pinbowling.dto.FrameDTO;
import com.marcelomnc.score10pinbowling.dto.GameDTO;
import com.marcelomnc.score10pinbowling.processor.PinFallsProcessor;
import com.marcelomnc.score10pinbowling.processor.PlayerChancesFileProcessor;
import com.marcelomnc.score10pinbowling.validator.CommandLineArgsValidator;
import com.marcelomnc.score10pinbowling.validator.SimpleCommandLineArgsValidator;

import java.io.File;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        LOGGER.log(Level.INFO, "Score 10-pin bowling app started");
        CommandLineArgsValidator commandLineArgsValidator = new SimpleCommandLineArgsValidator();

        LOGGER.log(Level.INFO, "Validating command line args ...");
        if (commandLineArgsValidator.isValid(args)) {
            LOGGER.log(Level.INFO, "Command line args are valid !");

            LOGGER.log(Level.INFO, "Validating file existence on path: " + args[0] + " ...");
            File f = new File(args[0]);
            if (f.exists()) {
                LOGGER.log(Level.INFO, "File existence on path: " + args[0] + " validated !");
                LOGGER.log(Level.INFO, "Processing score lines from file ...");
                PlayerChancesFileProcessor playerChancesFileProcessor = new PlayerChancesFileProcessor();
                Map<String, GameDTO> games = playerChancesFileProcessor.processPlayerChancesFile(args[0]);
                LOGGER.log(Level.INFO, "Score lines from file processed !");

                LOGGER.log(Level.INFO, "Processing pinfalls for each player game ...");
                PinFallsProcessor pinFallsProcessor = new PinFallsProcessor();
                pinFallsProcessor.processPinFalls(games);
                LOGGER.log(Level.INFO, "Pinfalls processed for each player game !");

                System.out.println("------------------------------------------");
                games.get("Jeff").getFrameDTOS().forEach(frameDTO -> {
                    System.out.println("\nFrame, pinfalls: " + frameDTO.getPinFalls().size());
                    frameDTO.getPinFalls().forEach(pinFallDTO -> {
                        System.out.print(pinFallDTO.getValue() + " - ");
                    });
                });
                System.out.println("------------------------------------------");


                //Printing frames header
                System.out.print(Constants.SCORE__FRAMES_LINE_LABEL);
                for (int i = 1; i <= 9; i++) {
                    System.out.print(Constants.SCORE__FRAMES_LINE_SEPARATOR + i);
                }
                System.out.println(Constants.SCORE__FRAMES_LINE_SEPARATOR + 10);

                games.forEach((gameOwner, gameDTO) -> {
                    System.out.println(gameDTO.getOwnerName());
                    System.out.print(Constants.SCORE__PINFALLS_LINE_LABEL + Constants.PINFALLS__VALUE_SEPARATOR);

                    for (int i = 0; i < gameDTO.getFrameDTOS().size(); i++) {
                        FrameDTO frame = gameDTO.getFrameDTOS().get(i);
                        String toPrint1, toPrint2, toPrint3 = null;
                        if (frame.getPinFalls().size() == 1) {
                            toPrint1 = Constants.PINFALLS__NO_CHANCE_INDICATOR;
                            toPrint1 += Constants.PINFALLS__VALUE_SEPARATOR;

                            toPrint2 = Constants.PINFALLS__STRIKE_INDICATOR;
                        } else if (frame.getPinFalls().size() == 2) {
                            toPrint1 = frame.getPinFalls().get(0).isFoul() ? Constants.PINFALLS__FOUL_INDICATOR : String.valueOf(frame.getPinFalls().get(0).getValue());
                            toPrint1 += Constants.PINFALLS__VALUE_SEPARATOR;

                            toPrint2 = frame.getPinFalls().get(1).isFoul() ? Constants.PINFALLS__FOUL_INDICATOR : String.valueOf(frame.getPinFalls().get(1).getValue());
                            if ((frame.getPinFalls().get(0).getValue() + frame.getPinFalls().get(1).getValue()) == 10) {
                                toPrint2 = Constants.PINFALLS__SPARE_INDICATOR;
                            }
                        } else {
                            //TODO: Pinfalls size is 3, last frame (frame 10)
                            toPrint1 = frame.getPinFalls().get(0).getValue() + Constants.PINFALLS__VALUE_SEPARATOR;
                            toPrint2 = frame.getPinFalls().get(1).getValue() + Constants.PINFALLS__VALUE_SEPARATOR;
                            toPrint3 = String.valueOf(frame.getPinFalls().get(2).getValue());
                        }

                        if (i == gameDTO.getFrameDTOS().size() - 1) {
                            //Last Frame
                            System.out.print(toPrint1);
                            System.out.print(toPrint2);
                            System.out.println(toPrint3);
                        } else {
                            System.out.print(toPrint1);
                            System.out.print(toPrint2 + Constants.PINFALLS__VALUE_SEPARATOR);
                        }
                    }
                });
            } else {
                LOGGER.log(Level.SEVERE, "File on path: " + args[0] + " does not exists.");
            }

        } else {
            LOGGER.log(Level.SEVERE, "Command line args are not valid.");
        }
    }
}
