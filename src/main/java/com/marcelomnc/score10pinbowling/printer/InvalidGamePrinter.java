package com.marcelomnc.score10pinbowling.printer;

import com.marcelomnc.score10pinbowling.common.Constants;
import com.marcelomnc.score10pinbowling.dto.GameDTO;

public class InvalidGamePrinter implements IGamePrinter {
    @Override
    public void printGame(GameDTO game) {
        //Printing player name
        System.out.println(game.getPlayerName());

        //Printing invalidation message
        System.out.println(Constants.GP_PRINT__INVALIDATION_MESSAGE_LABEL + game.getInvalidationMessage() + System.lineSeparator());
    }
}
