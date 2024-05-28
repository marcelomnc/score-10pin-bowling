package com.marcelomnc.score10pinbowling;

import com.marcelomnc.score10pinbowling.common.Constants;
import com.marcelomnc.score10pinbowling.dto.PlayerChancesFileParserResultDTO;
import com.marcelomnc.score10pinbowling.parser.IPlayerChancesFileParser;
import com.marcelomnc.score10pinbowling.parser.PlayerChancesFileParser;
import com.marcelomnc.score10pinbowling.printer.GamesPrinter;
import com.marcelomnc.score10pinbowling.printer.PlayerChancesFileErrorsPrinter;
import com.marcelomnc.score10pinbowling.processor.GamesProcessor;
import com.marcelomnc.score10pinbowling.validator.CommandLineArgsValidator;
import com.marcelomnc.score10pinbowling.validator.ICommandLineArgsValidator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //1. Command line args validating stage
        final ICommandLineArgsValidator commandLineArgsValidator = new CommandLineArgsValidator();
        if (!commandLineArgsValidator.isValid(args)) {
            System.out.println(Constants.CLA_VALIDATOR__ARGS_INVALID_MESSAGE);
            return;
        }

        //2. Parsing/validating data stage
        final File inputFile = new File(args[0]);
        final IPlayerChancesFileParser playerChancesFileParser = new PlayerChancesFileParser();

        final PlayerChancesFileParserResultDTO playerChancesFileParserResult;
        try {
            playerChancesFileParserResult = playerChancesFileParser.parsePlayerChancesFile(inputFile);
        } catch (IOException e) {
            System.out.printf(Constants.MAIN__IO_EXCEPTION_MESSAGE_PATTERN + System.lineSeparator(), inputFile.getPath());
            return;
        }

        if (playerChancesFileParserResult.getErrorLines().isEmpty()) {
            //3. Data processing/validating stage
            final GamesProcessor gamesProcessor = new GamesProcessor();
            gamesProcessor.processGames(new ArrayList<>(playerChancesFileParserResult.getGames().values()));

            //4. Printing data stage
            final GamesPrinter gamesPrinter = new GamesPrinter();
            gamesPrinter.printGames(
                    new ArrayList<>(playerChancesFileParserResult
                            .getGames()
                            .values()));

        } else {
            //Errors were found while parsing the input file. This is part of 4. Printing stage
            final PlayerChancesFileErrorsPrinter playerChancesFileErrorsPrinter = new PlayerChancesFileErrorsPrinter();
            playerChancesFileErrorsPrinter.printErrors(playerChancesFileParserResult.getErrorLines());
        }
    }
}
