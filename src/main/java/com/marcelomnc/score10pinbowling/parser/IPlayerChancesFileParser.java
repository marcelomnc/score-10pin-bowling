package com.marcelomnc.score10pinbowling.parser;

import com.marcelomnc.score10pinbowling.dto.PlayerChancesFileParserResult;

import java.io.IOException;

public interface IPlayerChancesFileParser {
    PlayerChancesFileParserResult parsePlayerChancesFile(String playerChancesFilePath) throws IOException;
}
