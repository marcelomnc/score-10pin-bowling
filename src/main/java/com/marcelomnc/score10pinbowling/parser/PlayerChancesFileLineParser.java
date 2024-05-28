package com.marcelomnc.score10pinbowling.parser;

import com.marcelomnc.score10pinbowling.common.Constants;
import com.marcelomnc.score10pinbowling.dto.PlayerChanceDTO;

class PlayerChancesFileLineParser implements IPlayerChancesFileLineParser {
    @Override
    public PlayerChanceDTO parseLine(String playerChancesFileLine) {
        final String[] fields = playerChancesFileLine.split(Constants.PLAYER_CHANCES_FILE__FIELD_SEPARATOR);

        if (fields[1].equals(Constants.PLAYER_CHANCES_FILE__FOUL_INDICATOR)) {
            //F stands for FOUL and means 0 pins were knocked down
            return new PlayerChanceDTO(fields[0], 0, true);
        } else {
            return new PlayerChanceDTO(fields[0], Integer.parseInt(fields[1]), false);
        }
    }
}
