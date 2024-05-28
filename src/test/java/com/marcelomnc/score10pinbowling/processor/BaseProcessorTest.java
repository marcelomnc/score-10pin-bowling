package com.marcelomnc.score10pinbowling.processor;

import com.marcelomnc.score10pinbowling.dto.GameDTO;
import com.marcelomnc.score10pinbowling.dto.PlayerChanceDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BaseProcessorTest {
    protected GameDTO buildAllFoulsGame(String playerName) {
        final GameDTO game = new GameDTO(playerName);
        final List<PlayerChanceDTO> playerChances = IntStream.range(0, 20)
                .mapToObj(operand -> new PlayerChanceDTO(playerName, 0, true))
                .collect(Collectors.toList());
        game.setPlayerChances(playerChances);
        return game;
    }

    protected GameDTO buildAllZeroesGame(String playerName) {
        final GameDTO game = new GameDTO(playerName);
        final List<PlayerChanceDTO> playerChances = IntStream.range(0, 20)
                .mapToObj(operand -> new PlayerChanceDTO(playerName, 0, false))
                .collect(Collectors.toList());
        game.setPlayerChances(playerChances);
        return game;
    }

    protected GameDTO buildPerfectGame(String playerName) {
        final GameDTO game = new GameDTO(playerName);
        final List<PlayerChanceDTO> playerChances = IntStream.range(0, 12)
                .mapToObj(operand -> new PlayerChanceDTO(playerName, 10, false))
                .collect(Collectors.toList());
        game.setPlayerChances(playerChances);
        return game;
    }
}
