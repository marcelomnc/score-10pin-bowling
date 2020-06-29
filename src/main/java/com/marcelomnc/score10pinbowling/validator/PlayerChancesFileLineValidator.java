package com.marcelomnc.score10pinbowling.validator;

import com.marcelomnc.score10pinbowling.common.Constants;
import com.marcelomnc.score10pinbowling.dto.PlayerChancesFileLineError;

public class PlayerChancesFileLineValidator implements IPlayerChancesFileLineValidator {

    private PlayerChancesFileLineError build(String playerChancesFileLine, int playerChancesFileLineNumber, String errorMessage) {
        return new PlayerChancesFileLineError(playerChancesFileLine, playerChancesFileLineNumber, errorMessage);
    }

    @Override
    public PlayerChancesFileLineError validate(String playerChancesFileLine, int playerChancesFileLineNumber) {
        PlayerChancesFileLineError toRet = null;

        if (playerChancesFileLine.indexOf(Constants.PLAYER_CHANCES_FILE__FIELD_SEPARATOR) == -1) {
            toRet = build(playerChancesFileLine, playerChancesFileLineNumber, Constants.PCFL_VALIDATOR__NO_FIELD_SEPARATOR_MESSAGE);
        } else {
            String[] fields = playerChancesFileLine.split(Constants.PLAYER_CHANCES_FILE__FIELD_SEPARATOR);
            if (fields.length != 2) {
                toRet = build(playerChancesFileLine, playerChancesFileLineNumber, Constants.PCFL_VALIDATOR__NO_2_FIELDS_ONLY_MESSAGE);
            } else {
                if (!fields[1].equals(Constants.PLAYER_CHANCES_FILE__FOUL_INDICATOR)) {
                    int knockedDownPins = 0;
                    try {
                        knockedDownPins = Integer.parseInt(fields[1]);
                        if (knockedDownPins < 0 || knockedDownPins > 10) {
                            toRet = build(playerChancesFileLine, playerChancesFileLineNumber, Constants.PCFL_VALIDATOR__FIELD_VALUE_0_10_MESSAGE);
                        }
                    } catch (NumberFormatException e) {
                        toRet = build(playerChancesFileLine, playerChancesFileLineNumber, Constants.PCFL_VALIDATOR__FIELD_VALUE_NOT_NUMBER_FOUL_MESSAGE);
                    }
                }
            }
        }

        return toRet;
    }
}
