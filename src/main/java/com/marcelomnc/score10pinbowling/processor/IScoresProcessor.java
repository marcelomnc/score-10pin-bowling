package com.marcelomnc.score10pinbowling.processor;

import com.marcelomnc.score10pinbowling.dto.GameDTO;

import java.util.Map;

public interface IScoresProcessor {
    void processScores(Map<String, GameDTO> games);
}
