package com.marcelomnc.score10pinbowling.processor;

import com.marcelomnc.score10pinbowling.dto.GameDTO;
import com.marcelomnc.score10pinbowling.dto.PlayerChanceDTO;
import com.marcelomnc.score10pinbowling.parser.PlayerChancesFileLineParser;
import com.marcelomnc.score10pinbowling.validator.PlayerChancesFileLineValidator;
import com.marcelomnc.score10pinbowling.validator.SimplePlayerChancesFileLineValidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class PlayerChancesFileProcessor {
    private static final Logger LOGGER = Logger.getLogger(PlayerChancesFileProcessor.class.getName());

    public Map<String, GameDTO> processPlayerChancesFile(String playerChancesFilePath) {
        Map<String, GameDTO> games = new LinkedHashMap<String, GameDTO>();

        try (Stream<String> scoreFileLines = Files.lines(Paths.get(playerChancesFilePath))) {
            PlayerChancesFileLineValidator playerChancesFileLineValidator = new SimplePlayerChancesFileLineValidator();
            PlayerChancesFileLineParser playerChancesFileLineParser = new PlayerChancesFileLineParser();

            scoreFileLines.forEach(scoreFileLine -> {
                if (playerChancesFileLineValidator.isValid(scoreFileLine)) {
                    PlayerChanceDTO playerChanceDTO = playerChancesFileLineParser.processLine(scoreFileLine);
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
        } catch (IOException e) {
            //TODO: Que hacer ?
            e.printStackTrace();
        }

        return games;
    }
}
