package com.marcelomnc.score10pinbowling.parser;

import com.marcelomnc.score10pinbowling.dto.PlayerChanceDTO;

interface IPlayerChancesFileLineParser {
    PlayerChanceDTO parseLine(String playerChancesFileLine);
}
