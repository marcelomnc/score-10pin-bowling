package com.marcelomnc.score10pinbowling.validator;

import com.marcelomnc.score10pinbowling.dto.PlayerChancesFileLineError;

public interface IPlayerChancesFileLineValidator {
    PlayerChancesFileLineError validate(String playerChancesFileLine, int playerChancesFileLineNumber);
}
