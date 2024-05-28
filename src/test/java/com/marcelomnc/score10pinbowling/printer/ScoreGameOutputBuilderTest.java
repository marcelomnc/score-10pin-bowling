package com.marcelomnc.score10pinbowling.printer;

import com.marcelomnc.score10pinbowling.common.Constants;
import com.marcelomnc.score10pinbowling.dto.FrameDTO;
import com.marcelomnc.score10pinbowling.dto.GameDTO;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ScoreGameOutputBuilderTest {
    private final ScoreGameOutputBuilder toTest = new ScoreGameOutputBuilder();

    public GameDTO buildAllZeroesFrameGame(String playerName) {
        final GameDTO game = new GameDTO(playerName);
        final FrameDTO zeroScoreFrameDTO = new FrameDTO(0);
        //For all 10 frames
        final List<FrameDTO> frames = IntStream
                .range(0, 10)
                .mapToObj(value -> zeroScoreFrameDTO)
                .collect(Collectors.toList());
        game.getFrames().addAll(frames);
        return game;
    }

    public GameDTO buildPerfectScoreFrameGame(String playerName) {
        final GameDTO game = new GameDTO(playerName);
        final List<FrameDTO> frames = new ArrayList<>(10);
        final FrameDTO frame1 = new FrameDTO(30);
        final FrameDTO frame2 = new FrameDTO(60);
        final FrameDTO frame3 = new FrameDTO(90);
        final FrameDTO frame4 = new FrameDTO(120);
        final FrameDTO frame5 = new FrameDTO(150);
        final FrameDTO frame6 = new FrameDTO(180);
        final FrameDTO frame7 = new FrameDTO(210);
        final FrameDTO frame8 = new FrameDTO(240);
        final FrameDTO frame9 = new FrameDTO(270);
        final FrameDTO frame10 = new FrameDTO(300);

        frames.add(frame1);
        frames.add(frame2);
        frames.add(frame3);
        frames.add(frame4);
        frames.add(frame5);
        frames.add(frame6);
        frames.add(frame7);
        frames.add(frame8);
        frames.add(frame9);
        frames.add(frame10);

        game.getFrames().addAll(frames);
        return game;
    }

    @Test
    public void testSuccessfulOutputOnAllZeroesScore() {
        final String playerName = "Ellie";
        final GameDTO game = this.buildAllZeroesFrameGame(playerName);
        final String output = this.toTest.buildOutput(game);
        Assert.assertEquals(
                Constants.PRINT__SCORE_LABEL +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        0 +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        0 +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        0 +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        0 +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        0 +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        0 +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        0 +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        0 +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        0 +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        0,
                output
        );
    }

    @Test
    public void testSuccessfulOutputOnPerfectScore() {
        final String playerName = "Alex";
        final GameDTO game = this.buildPerfectScoreFrameGame(playerName);
        final String output = this.toTest.buildOutput(game);
        Assert.assertEquals(
                Constants.PRINT__SCORE_LABEL +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        game.getFrames().get(0).getScore() +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        game.getFrames().get(1).getScore() +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        game.getFrames().get(2).getScore() +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        game.getFrames().get(3).getScore() +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        game.getFrames().get(4).getScore() +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        game.getFrames().get(5).getScore() +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        game.getFrames().get(6).getScore() +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        game.getFrames().get(7).getScore() +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        game.getFrames().get(8).getScore() +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        game.getFrames().get(9).getScore(),
                output
        );
    }
}
