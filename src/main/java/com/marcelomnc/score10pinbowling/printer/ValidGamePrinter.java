package com.marcelomnc.score10pinbowling.printer;

import com.marcelomnc.score10pinbowling.dto.GameDTO;

public class ValidGamePrinter implements IGamePrinter {
    private final IGameOutputBuilder pinFallsOutputBuilder;
    private final IGameOutputBuilder scoreOutputBuilder;

    public ValidGamePrinter() {
        this(new PinFallsGameOutputBuilder(), new ScoreGameOutputBuilder());
    }

    ValidGamePrinter(IGameOutputBuilder pinFallsOutputBuilder, IGameOutputBuilder scoreOutputBuilder) {
        this.pinFallsOutputBuilder = pinFallsOutputBuilder;
        this.scoreOutputBuilder = scoreOutputBuilder;
    }

    @Override
    public void printGame(GameDTO game) {
        final String pinFallsOutput = this.pinFallsOutputBuilder.buildOutput(game);
        final String scoreOutput = this.scoreOutputBuilder.buildOutput(game);

        //Printing player name
        System.out.println(game.getPlayerName());

        //Printing pinFalls
        System.out.println(pinFallsOutput);

        //Printing scores
        System.out.println(scoreOutput);
    }
}
