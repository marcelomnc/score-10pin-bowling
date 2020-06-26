package com.marcelomnc.score10pinbowling.processor;

import com.marcelomnc.score10pinbowling.dto.GameDTO;

import java.util.Map;

public interface IPinFallsProcessor {
    void processPinFalls(Map<String, GameDTO> games);
}
