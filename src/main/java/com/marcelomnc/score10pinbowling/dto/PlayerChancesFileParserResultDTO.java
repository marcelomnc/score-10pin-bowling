package com.marcelomnc.score10pinbowling.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class PlayerChancesFileParserResultDTO implements Serializable {
    private final List<PlayerChancesFileErrorLineDTO> errorLines;
    private final Map<String, GameDTO> games;

    public PlayerChancesFileParserResultDTO(List<PlayerChancesFileErrorLineDTO> errorLines, Map<String, GameDTO> games) {
        this.errorLines = errorLines;
        this.games = games;
    }

    public List<PlayerChancesFileErrorLineDTO> getErrorLines() {
        return errorLines;
    }

    public Map<String, GameDTO> getGames() {
        return games;
    }
}
