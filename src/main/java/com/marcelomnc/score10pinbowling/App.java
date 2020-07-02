package com.marcelomnc.score10pinbowling;

import com.marcelomnc.score10pinbowling.dto.GameDTO;
import com.marcelomnc.score10pinbowling.dto.PlayerChancesFileParserResult;
import com.marcelomnc.score10pinbowling.parser.IPlayerChancesFileParser;
import com.marcelomnc.score10pinbowling.parser.PlayerChancesFileParser;
import com.marcelomnc.score10pinbowling.printer.IGamesPrinter;
import com.marcelomnc.score10pinbowling.printer.IPlayerChancesFileLineErrorsPrinter;
import com.marcelomnc.score10pinbowling.printer.PlayerChancesFileLineErrorsPrinter;
import com.marcelomnc.score10pinbowling.printer.SimpleFormatGamePrinter;
import com.marcelomnc.score10pinbowling.processor.IPinFallsProcessor;
import com.marcelomnc.score10pinbowling.processor.IScoresProcessor;
import com.marcelomnc.score10pinbowling.processor.PinFallsProcessor;
import com.marcelomnc.score10pinbowling.processor.ScoresProcessor;
import com.marcelomnc.score10pinbowling.validator.CommandLineArgsValidator;
import com.marcelomnc.score10pinbowling.validator.ICommandLineArgsValidator;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class App {
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        LogManager.getLogManager().reset();
        Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
        globalLogger.setLevel(java.util.logging.Level.OFF);

        LOGGER.log(Level.INFO, "Score 10-pin bowling app started");
        ICommandLineArgsValidator ICommandLineArgsValidator = new CommandLineArgsValidator();

        LOGGER.log(Level.INFO, "Validating command line args ...");
        if (ICommandLineArgsValidator.isValid(args)) {
            LOGGER.log(Level.INFO, "Command line args are valid !");

            LOGGER.log(Level.INFO, "Validating file existence on path: " + args[0] + " ...");
            File f = new File(args[0]);
            if (f.exists()) {
                try {
                    LOGGER.log(Level.INFO, "File existence on path: " + args[0] + " validated !");

                    LOGGER.log(Level.INFO, "Parsing player chances file ...");
                    IPlayerChancesFileParser playerChancesFileParser = new PlayerChancesFileParser();
                    PlayerChancesFileParserResult result = playerChancesFileParser.parsePlayerChancesFile(args[0]);
                    if (result.getErrors().isEmpty()) {
                        LOGGER.log(Level.INFO, "Player chances file parsed !");
                        Map<String, GameDTO> games = result.getGames();

                        LOGGER.log(Level.INFO, "Processing pinFalls for each player game ...");
                        IPinFallsProcessor pinFallsProcessor = new PinFallsProcessor();
                        pinFallsProcessor.processPinFalls(games);
                        LOGGER.log(Level.INFO, "PinFalls processed for each player game !");

                        LOGGER.log(Level.INFO, "Processing scores for each player game ...");
                        IScoresProcessor scoresProcessor = new ScoresProcessor();
                        scoresProcessor.processScores(games);
                        LOGGER.log(Level.INFO, "Scores processed for each player game !");

                        boolean hasValidGames = false;
                        for (Map.Entry<String, GameDTO> entry : games.entrySet()) {
                            if (entry.getValue().isValid()) {
                                hasValidGames = true;
                                break;
                            }
                        }

                        if (hasValidGames) {
                            LOGGER.log(Level.INFO, "Printing games ...");
                            IGamesPrinter gamesPrinter = new SimpleFormatGamePrinter();
                            gamesPrinter.printGames(games);
                            LOGGER.log(Level.INFO, "Games printed !");
                        } else {
                            String message = "No valid games to print";
                            System.out.println(message);
                            LOGGER.log(Level.SEVERE, message);
                        }
                    } else {
                        //Player Chances File has lines with errors
                        LOGGER.log(Level.INFO, "Player chances file has lines with errors, printing errors ...");
                        IPlayerChancesFileLineErrorsPrinter playerChancesFileLineErrorsPrinter = new PlayerChancesFileLineErrorsPrinter();
                        playerChancesFileLineErrorsPrinter.printErrors(result.getErrors());
                        LOGGER.log(Level.INFO, "Errors printed !");
                    }
                } catch (IOException e) {
                    String message = "An IOException ocurred while parsing the player chances file on path: " + args[0] + ". App cannot continue";
                    LOGGER.log(Level.SEVERE, message, e);
                    System.out.println(message);
                }
            } else {
                String message = "File on path: " + args[0] + " does not exists. App cannot continue";
                LOGGER.log(Level.SEVERE, "File on path: " + args[0] + " does not exists. App cannot continue");
                System.out.println(message);
            }
        } else {
            String message = "Command line args are not valid. App cannot continue";
            LOGGER.log(Level.SEVERE, message);
            System.out.println(message);
        }

        LOGGER.log(Level.INFO, "Score 10-pin bowling app finished");
    }
}