package com.marcelomnc.score10pinbowling.processor;

import com.marcelomnc.score10pinbowling.dto.GameDTO;

public interface IGameProcessor {
    void processGame(GameDTO game);
}
