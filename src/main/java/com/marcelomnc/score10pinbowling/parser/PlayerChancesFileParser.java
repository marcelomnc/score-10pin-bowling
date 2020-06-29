package com.marcelomnc.score10pinbowling.parser;

import com.marcelomnc.score10pinbowling.dto.GameDTO;
import com.marcelomnc.score10pinbowling.dto.PlayerChanceDTO;
import com.marcelomnc.score10pinbowling.validator.IPlayerChancesFileLineValidator;
import com.marcelomnc.score10pinbowling.validator.PlayerChancesFileLineValidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class PlayerChancesFileParser implements IPlayerChancesFileParser {
    private static final Logger LOGGER = Logger.getLogger(PlayerChancesFileParser.class.getName());

    @Override
    public Map<String, GameDTO> parsePlayerChancesFile(String playerChancesFilePath) throws IOException {
        Map<String, GameDTO> games = new LinkedHashMap<String, GameDTO>();

        try (Stream<String> scoreFileLines = Files.lines(Paths.get(playerChancesFilePath))) {
            IPlayerChancesFileLineValidator IPlayerChancesFileLineValidator = new PlayerChancesFileLineValidator();
            PlayerChancesFileLineParser playerChancesFileLineParser = new PlayerChancesFileLineParser();

            scoreFileLines.forEach(scoreFileLine -> {
                if (IPlayerChancesFileLineValidator.isValid(scoreFileLine)) {
                    PlayerChanceDTO playerChanceDTO = playerChancesFileLineParser.parseLine(scoreFileLine);
                    GameDTO gameDTO = games.get(playerChanceDTO.getPlayerName());

                    if (gameDTO == null) {
                        gameDTO = new GameDTO(playerChanceDTO.getPlayerName());
                        games.put(playerChanceDTO.getPlayerName(), gameDTO);
                    }

                    gameDTO.getPlayerChanceDTOs().add(playerChanceDTO);
                } else {
                    LOGGER.log(Level.SEVERE, "Invalid Line: " + scoreFileLine);
                }
            });
        }

        return games;
    }
}
