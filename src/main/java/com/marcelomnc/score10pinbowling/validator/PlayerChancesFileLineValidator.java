package com.marcelomnc.score10pinbowling.validator;

import com.marcelomnc.score10pinbowling.common.Constants;
import com.marcelomnc.score10pinbowling.dto.PlayerChancesFileErrorLineDTO;

import java.util.Optional;

public class PlayerChancesFileLineValidator implements IPlayerChancesFileLineValidator {
    @Override
    public Optional<PlayerChancesFileErrorLineDTO> validate(String playerChancesFileLine, int playerChancesFileLineNumber) {
        if (!playerChancesFileLine.contains(Constants.PLAYER_CHANCES_FILE__FIELD_SEPARATOR)) {
            //Line must have the separator, since it must contain 2 field values
            return Optional.of(new PlayerChancesFileErrorLineDTO(playerChancesFileLine, playerChancesFileLineNumber, Constants.PCFL_VALIDATOR__NO_FIELD_SEPARATOR_MESSAGE));
        }

        final String[] fields = playerChancesFileLine.split(Constants.PLAYER_CHANCES_FILE__FIELD_SEPARATOR);
        if (fields.length != 2) {
            //There must be 2 and only 2 fields on each line
            return Optional.of(new PlayerChancesFileErrorLineDTO(playerChancesFileLine, playerChancesFileLineNumber, Constants.PCFL_VALIDATOR__2_FIELDS_ONLY_MESSAGE));
        }

        if (!fields[1].equals(Constants.PLAYER_CHANCES_FILE__FOUL_INDICATOR)) {
            try {
                final int knockedDownPins = Integer.parseInt(fields[1]);
                if (knockedDownPins < 0 || knockedDownPins > 10) {
                    //Score value must be between 0 and 10
                    return Optional.of(new PlayerChancesFileErrorLineDTO(playerChancesFileLine, playerChancesFileLineNumber, String.format(Constants.PCFL_VALIDATOR__FIELD_VALUE_0_10_MESSAGE, knockedDownPins)));
                }
            } catch (NumberFormatException e) {
                //The only non-numeric value accepted is the F which stands for Foul
                return Optional.of(new PlayerChancesFileErrorLineDTO(playerChancesFileLine, playerChancesFileLineNumber, String.format(Constants.PCFL_VALIDATOR__FIELD_VALUE_NOT_NUMBER_NOR_FOUL_MESSAGE, fields[1])));
            }
        }

        return Optional.empty();
    }
}
