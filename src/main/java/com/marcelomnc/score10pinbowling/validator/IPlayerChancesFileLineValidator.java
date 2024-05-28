package com.marcelomnc.score10pinbowling.validator;

import com.marcelomnc.score10pinbowling.dto.PlayerChancesFileErrorLineDTO;

import java.util.Optional;

public interface IPlayerChancesFileLineValidator {
    Optional<PlayerChancesFileErrorLineDTO> validate(String playerChancesFileLine, int playerChancesFileLineNumber);
}
