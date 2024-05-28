package com.marcelomnc.score10pinbowling.processor;

import com.marcelomnc.score10pinbowling.dto.GameDTO;

import java.util.List;

public class GamesProcessor {
    private final IGameProcessor pinFallsProcessor;
    private final IGameProcessor scoreProcessor;

    public GamesProcessor() {
        this(new PinFallsProcessor(), new ScoreProcessor());
    }

    GamesProcessor(IGameProcessor pinFallsProcessor, IGameProcessor scoreProcessor) {
        this.pinFallsProcessor = pinFallsProcessor;
        this.scoreProcessor = scoreProcessor;
    }

    public void processGames(List<GameDTO> games) {
        games.forEach(game -> {
            //Process pinFalls data
            pinFallsProcessor.processGame(game);

            if (game.isValid()) {
                //Process score data
                scoreProcessor.processGame(game);
            }
        });
    }
}
