package com.marcelomnc.score10pinbowling.printer;

import com.marcelomnc.score10pinbowling.dto.PlayerChancesFileLineError;

import java.util.List;

public interface IPlayerChancesFileLineErrorsPrinter {
    void printErrors(List<PlayerChancesFileLineError> errors);
}
