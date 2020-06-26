package com.marcelomnc.score10pinbowling.validator;

import com.marcelomnc.score10pinbowling.common.Constants;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerChancesFileLineValidator implements IPlayerChancesFileLineValidator {
    private static final Logger LOGGER = Logger.getLogger(PlayerChancesFileLineValidator.class.getName());

    @Override
    public boolean isValid(String playerChancesFileLine) {
        boolean toRet = true;

        if (playerChancesFileLine.indexOf(Constants.PLAYER_CHANCES_FILE__FIELD_SEPARATOR) == -1) {
            toRet = false;
            LOGGER.log(Level.SEVERE, "Line has no tab separator");
        } else {
            String[] fields = playerChancesFileLine.split(Constants.PLAYER_CHANCES_FILE__FIELD_SEPARATOR);
            if (fields.length != 2) {
                toRet = false;
                LOGGER.log(Level.SEVERE, "Line has no 2 fields only");
            } else {
                if (!fields[1].equals(Constants.PLAYER_CHANCES_FILE__FOUL_INDICATOR)) {
                    int score = 0;
                    try {
                        score = Integer.parseInt(fields[1]);
                        if (score < 0 || score > 10) {
                            toRet = false;
                            LOGGER.log(Level.SEVERE, "Line has a score that is not between 0 and 10");
                        }
                    } catch (NumberFormatException e) {
                        toRet = false;
                        LOGGER.log(Level.SEVERE, "Line has a score that is not a number and not an F");
                    }
                }
            }
        }

        return toRet;
    }
}
