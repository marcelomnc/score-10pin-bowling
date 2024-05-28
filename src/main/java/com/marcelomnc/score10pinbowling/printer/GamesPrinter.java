package com.marcelomnc.score10pinbowling.printer;

import com.marcelomnc.score10pinbowling.common.Constants;
import com.marcelomnc.score10pinbowling.dto.GameDTO;

import java.util.List;

public class GamesPrinter {
    private final IGamePrinter validGamePrinter;
    private final IGamePrinter invalidGamePrinter;

    public GamesPrinter() {
        this(new ValidGamePrinter(), new InvalidGamePrinter());
    }

    GamesPrinter(IGamePrinter validGamePrinter, IGamePrinter invalidGamePrinter) {
        this.validGamePrinter = validGamePrinter;
        this.invalidGamePrinter = invalidGamePrinter;
    }

    public void printGames(List<GameDTO> games) {
        final long validGames = games.stream().filter(GameDTO::isValid).count();

        if (validGames > 0) {
            //Printing frames header
            System.out.println(this.buildFramesHeaderOutput());

            //Printing valid games
            games
                    .stream()
                    //Only print valid games
                    .filter(GameDTO::isValid)
                    .forEach(this.validGamePrinter::printGame);
        } else {
            System.out.println(Constants.GP_PRINT__NO_VALID_GAMES_TO_PRINT_MESSAGE);
        }

        if (validGames < games.size()) {
            //There are invalid games, print them
            System.out.println(System.lineSeparator() +
                    Constants.GP_PRINT__INVALID_GAMES_TITLE +
                    System.lineSeparator());

            games
                    .stream()
                    //Only print invalid games
                    .filter(game -> !game.isValid())
                    .forEach(this.invalidGamePrinter::printGame);
        }
    }

    private String buildFramesHeaderOutput() {
        final StringBuilder sb = new StringBuilder();
        sb.append(Constants.PRINT__FRAME_LABEL);
        sb.append(Constants.PRINT__FIELD_SEPARATOR);
        sb.append(Constants.PRINT__FIELD_SEPARATOR);
        for (int i = 1; i <= 9; i++) {
            sb.append(i);
            sb.append(Constants.PRINT__FIELD_SEPARATOR);
            sb.append(Constants.PRINT__FIELD_SEPARATOR);
        }
        sb.append(10);

        return sb.toString();
    }
}
