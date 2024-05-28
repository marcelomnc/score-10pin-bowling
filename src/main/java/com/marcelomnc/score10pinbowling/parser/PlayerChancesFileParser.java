package com.marcelomnc.score10pinbowling.parser;

import com.marcelomnc.score10pinbowling.common.Constants;
import com.marcelomnc.score10pinbowling.dto.GameDTO;
import com.marcelomnc.score10pinbowling.dto.PlayerChanceDTO;
import com.marcelomnc.score10pinbowling.dto.PlayerChancesFileErrorLineDTO;
import com.marcelomnc.score10pinbowling.dto.PlayerChancesFileParserResultDTO;
import com.marcelomnc.score10pinbowling.validator.IPlayerChancesFileLineValidator;
import com.marcelomnc.score10pinbowling.validator.PlayerChancesFileLineValidator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PlayerChancesFileParser implements IPlayerChancesFileParser {
    final IPlayerChancesFileLineValidator playerChancesFileLineValidator;
    final IPlayerChancesFileLineParser playerChancesFileLineParser;

    public PlayerChancesFileParser() {
        this(new PlayerChancesFileLineValidator(), new PlayerChancesFileLineParser());
    }

    PlayerChancesFileParser(IPlayerChancesFileLineValidator playerChancesFileLineValidator, IPlayerChancesFileLineParser playerChancesFileLineParser) {
        this.playerChancesFileLineValidator = playerChancesFileLineValidator;
        this.playerChancesFileLineParser = playerChancesFileLineParser;
    }

    @Override
    public PlayerChancesFileParserResultDTO parsePlayerChancesFile(File playerChancesFile) throws IOException {
        if (!playerChancesFile.exists()) {
            return this.buildResultWithGeneralError(String.format(Constants.PCFL_VALIDATOR__FILE_DOES_NOT_EXISTS_MESSAGE_PATTERN, playerChancesFile.getPath()));
        }

        if (playerChancesFile.length() == 0) {
            return this.buildResultWithGeneralError(String.format(Constants.PCFL_VALIDATOR__FILE_IS_EMPTY_MESSAGE_PATTERN, playerChancesFile.getPath()));
        }

        final List<PlayerChancesFileErrorLineDTO> errorLines = new ArrayList<>();
        //Use linked hashmap to keep the player games order same as how the chances appear inside the file, key is the player's name
        final Map<String, GameDTO> parsedGames = new LinkedHashMap<>();

        int currentLineNumber = 1;
        //Using the old way of reading files just to be able to grab the line number for the errors report
        try (final FileReader fr = new FileReader(playerChancesFile);
             final BufferedReader buff = new BufferedReader(fr)) {
            String playerChancesFileLine = buff.readLine();

            while (playerChancesFileLine != null) {
                final String currentLine = playerChancesFileLine;
                this.playerChancesFileLineValidator.validate(currentLine, currentLineNumber)
                        .map(
                                //Line is not valid
                                errorLines::add
                        )
                        .orElseGet(() -> {
                            final PlayerChanceDTO playerChanceDTO = this.playerChancesFileLineParser.parseLine(currentLine);
                            GameDTO gameDTO = parsedGames.get(playerChanceDTO.getPlayerName());

                            if (gameDTO == null) {
                                gameDTO = new GameDTO(playerChanceDTO.getPlayerName());
                                parsedGames.put(playerChanceDTO.getPlayerName(), gameDTO);
                            }

                            gameDTO.getPlayerChances().add(playerChanceDTO);
                            return null;
                        });

                playerChancesFileLine = buff.readLine();
                currentLineNumber++;
            }
        }

        return new PlayerChancesFileParserResultDTO(errorLines, parsedGames);
    }

    private PlayerChancesFileParserResultDTO buildResultWithGeneralError(String errorMessage) {
        final PlayerChancesFileErrorLineDTO playerChancesFileErrorLine = new PlayerChancesFileErrorLineDTO(null, 1, errorMessage);
        playerChancesFileErrorLine.setGeneral(true);
        return new PlayerChancesFileParserResultDTO(
                Collections.singletonList(playerChancesFileErrorLine),
                Collections.emptyMap());
    }
}
