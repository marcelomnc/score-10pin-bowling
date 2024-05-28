package com.marcelomnc.score10pinbowling.printer;

import com.marcelomnc.score10pinbowling.dto.GameDTO;

public interface IGameOutputBuilder {
    String buildOutput(GameDTO game);
}
