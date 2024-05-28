package com.marcelomnc.score10pinbowling.parser;

import com.marcelomnc.score10pinbowling.dto.PlayerChancesFileParserResultDTO;

import java.io.File;
import java.io.IOException;

public interface IPlayerChancesFileParser {
    PlayerChancesFileParserResultDTO parsePlayerChancesFile(File playerChancesFile) throws IOException;
}
