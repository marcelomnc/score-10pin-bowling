package com.marcelomnc.score10pinbowling.dto;

import java.util.List;
import java.util.Map;

public class PlayerChancesFileParserResult {
    private List<PlayerChancesFileLineError> errors;
    private Map<String, GameDTO> games;

    public PlayerChancesFileParserResult(List<PlayerChancesFileLineError> errors, Map<String, GameDTO> games) {
        this.errors = errors;
        this.games = games;
    }

    public List<PlayerChancesFileLineError> getErrors() {
        return errors;
    }

    public void setErrors(List<PlayerChancesFileLineError> errors) {
        this.errors = errors;
    }

    public Map<String, GameDTO> getGames() {
        return games;
    }

    public void setGames(Map<String, GameDTO> games) {
        this.games = games;
    }
}
