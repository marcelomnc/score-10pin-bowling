package com.marcelomnc.score10pinbowling.printer;

import com.marcelomnc.score10pinbowling.dto.GameDTO;

import java.util.Map;

public interface IGamesPrinter {
    void printGames(Map<String, GameDTO> games);
}
