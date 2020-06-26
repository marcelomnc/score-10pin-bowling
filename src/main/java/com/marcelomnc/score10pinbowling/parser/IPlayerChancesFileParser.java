package com.marcelomnc.score10pinbowling.parser;

import com.marcelomnc.score10pinbowling.dto.GameDTO;

import java.util.Map;

public interface IPlayerChancesFileParser {
    Map<String, GameDTO> parsePlayerChancesFile(String playerChancesFilePath);
}
