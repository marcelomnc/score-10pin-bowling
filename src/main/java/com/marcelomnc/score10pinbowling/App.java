package com.marcelomnc.score10pinbowling;

import com.marcelomnc.score10pinbowling.dto.GameDTO;
import com.marcelomnc.score10pinbowling.parser.IPlayerChancesFileParser;
import com.marcelomnc.score10pinbowling.parser.PlayerChancesFileParser;
import com.marcelomnc.score10pinbowling.printer.GamesPrinter;
import com.marcelomnc.score10pinbowling.printer.IGamesPrinter;
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
import java.util.logging.Logger;

public class App {
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
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
                    Map<String, GameDTO> games = playerChancesFileParser.parsePlayerChancesFile(args[0]);
                    LOGGER.log(Level.INFO, "Player chances file parsed !");

                    LOGGER.log(Level.INFO, "Processing pinFalls for each player game ...");
                    IPinFallsProcessor pinFallsProcessor = new PinFallsProcessor();
                    pinFallsProcessor.processPinFalls(games);
                    LOGGER.log(Level.INFO, "PinFalls processed for each player game !");

                    LOGGER.log(Level.INFO, "Processing scores for each player game ...");
                    IScoresProcessor scoresProcessor = new ScoresProcessor();
                    scoresProcessor.processScores(games);
                    LOGGER.log(Level.INFO, "Scores processed for each player game !");

                    LOGGER.log(Level.INFO, "Printing games ...");
                    IGamesPrinter gamesPrinter = new GamesPrinter();
                    gamesPrinter.printGames(games);
                    LOGGER.log(Level.INFO, "Games printed !");
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, "IO exception while parsing player chances file on path: " + args[0] + ". App cannot continue.", e);
                }
            } else {
                LOGGER.log(Level.SEVERE, "File on path: " + args[0] + " does not exists. App cannot continue.");
            }
        } else {
            LOGGER.log(Level.SEVERE, "Command line args are not valid.");
        }

        LOGGER.log(Level.INFO, "Score 10-pin bowling app finished");
    }
}
