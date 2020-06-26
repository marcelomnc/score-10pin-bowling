package com.marcelomnc.score10pinbowling.parser;

import com.marcelomnc.score10pinbowling.common.Constants;
import com.marcelomnc.score10pinbowling.dto.PlayerChanceDTO;

public class PlayerChancesFileLineParser {

    public PlayerChanceDTO parseLine(String scoreLine) {
        PlayerChanceDTO toRet = new PlayerChanceDTO();
        String[] fields = scoreLine.split(Constants.PLAYER_CHANCES_FILE__FIELD_SEPARATOR);
        toRet.setPlayerName(fields[0]);
        if (fields[1].equals(Constants.PLAYER_CHANCES_FILE__FOUL_INDICATOR)) {
            //F stands for FOUL and means 0 pins fell
            toRet.setFoul(true);
            toRet.setKnockedDownPins(0);
        } else {
            toRet.setKnockedDownPins(Integer.valueOf(fields[1]));
        }
        return toRet;
    }
}
