package com.marcelomnc.score10pinbowling.printer;

import com.marcelomnc.score10pinbowling.common.Constants;
import com.marcelomnc.score10pinbowling.dto.FrameDTO;
import com.marcelomnc.score10pinbowling.dto.GameDTO;
import com.marcelomnc.score10pinbowling.dto.PinFallDTO;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PinFallsGameOutputBuilderTest {
    private final PinFallsGameOutputBuilder toTest = new PinFallsGameOutputBuilder();

    private GameDTO buildPinFallsForAllFoulsGame(String playerName) {
        final GameDTO game = new GameDTO(playerName);
        final PinFallDTO foulPinFall = new PinFallDTO(true, 0);

        //For all 10 frames
        final List<FrameDTO> frames = IntStream.range(0, 10)
                .mapToObj(value -> {
                    final FrameDTO frameDTO = new FrameDTO();
                    frameDTO.getPinFalls().add(foulPinFall);
                    frameDTO.getPinFalls().add(foulPinFall);
                    return frameDTO;
                }).collect(Collectors.toList());

        game.getFrames().addAll(frames);
        return game;
    }

    private GameDTO buildPinFallsForAllZeroesGame(String playerName) {
        final GameDTO game = new GameDTO(playerName);
        final PinFallDTO zeroPinFall = new PinFallDTO(false, 0);

        //For all 10 frames
        final List<FrameDTO> frames = IntStream.range(0, 10)
                .mapToObj(value -> {
                    final FrameDTO frameDTO = new FrameDTO();
                    frameDTO.getPinFalls().add(zeroPinFall);
                    frameDTO.getPinFalls().add(zeroPinFall);
                    return frameDTO;
                }).collect(Collectors.toList());

        game.getFrames().addAll(frames);
        return game;
    }


    private GameDTO buildPinFallsForPerfectGame(String playerName) {
        final GameDTO game = new GameDTO(playerName);
        final PinFallDTO perfectPinFall = new PinFallDTO(false, 10);

        //For the first 9 frames
        final List<FrameDTO> frames = IntStream.range(0, 9)
                .mapToObj(value -> {
                    final FrameDTO frameDTO = new FrameDTO();
                    frameDTO.getPinFalls().add(perfectPinFall);
                    return frameDTO;
                }).collect(Collectors.toList());

        final FrameDTO lastFrame = new FrameDTO();
        lastFrame.getPinFalls().add(perfectPinFall);
        lastFrame.getPinFalls().add(perfectPinFall);
        lastFrame.getPinFalls().add(perfectPinFall);

        frames.add(lastFrame);

        game.getFrames().addAll(frames);
        return game;
    }

    @Test
    public void testSuccessfulOutputOnAllFoulsGame() {
        final String playerName = "Myriam";
        final GameDTO game = this.buildPinFallsForAllFoulsGame(playerName);
        final String output = this.toTest.buildOutput(game);

        final String expected =
                Constants.PRINT__PINFALLS_LABEL +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__PINFALLS_FOUL_INDICATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__PINFALLS_FOUL_INDICATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__PINFALLS_FOUL_INDICATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__PINFALLS_FOUL_INDICATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__PINFALLS_FOUL_INDICATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__PINFALLS_FOUL_INDICATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__PINFALLS_FOUL_INDICATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__PINFALLS_FOUL_INDICATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__PINFALLS_FOUL_INDICATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__PINFALLS_FOUL_INDICATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__PINFALLS_FOUL_INDICATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__PINFALLS_FOUL_INDICATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__PINFALLS_FOUL_INDICATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__PINFALLS_FOUL_INDICATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__PINFALLS_FOUL_INDICATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__PINFALLS_FOUL_INDICATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__PINFALLS_FOUL_INDICATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__PINFALLS_FOUL_INDICATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__PINFALLS_FOUL_INDICATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__PINFALLS_FOUL_INDICATOR;

        Assert.assertEquals(expected, output);
    }

    @Test
    public void testSuccessfulOutputOnAllZeroesGame() {
        final String playerName = "Peter";
        final GameDTO game = this.buildPinFallsForAllZeroesGame(playerName);
        final String output = this.toTest.buildOutput(game);

        final String expected =
                Constants.PRINT__PINFALLS_LABEL +
                        Constants.PRINT__FIELD_SEPARATOR +
                        0 +
                        Constants.PRINT__FIELD_SEPARATOR +
                        0 +
                        Constants.PRINT__FIELD_SEPARATOR +
                        0 +
                        Constants.PRINT__FIELD_SEPARATOR +
                        0 +
                        Constants.PRINT__FIELD_SEPARATOR +
                        0 +
                        Constants.PRINT__FIELD_SEPARATOR +
                        0 +
                        Constants.PRINT__FIELD_SEPARATOR +
                        0 +
                        Constants.PRINT__FIELD_SEPARATOR +
                        0 +
                        Constants.PRINT__FIELD_SEPARATOR +
                        0 +
                        Constants.PRINT__FIELD_SEPARATOR +
                        0 +
                        Constants.PRINT__FIELD_SEPARATOR +
                        0 +
                        Constants.PRINT__FIELD_SEPARATOR +
                        0 +
                        Constants.PRINT__FIELD_SEPARATOR +
                        0 +
                        Constants.PRINT__FIELD_SEPARATOR +
                        0 +
                        Constants.PRINT__FIELD_SEPARATOR +
                        0 +
                        Constants.PRINT__FIELD_SEPARATOR +
                        0 +
                        Constants.PRINT__FIELD_SEPARATOR +
                        0 +
                        Constants.PRINT__FIELD_SEPARATOR +
                        0 +
                        Constants.PRINT__FIELD_SEPARATOR +
                        0 +
                        Constants.PRINT__FIELD_SEPARATOR +
                        0;

        Assert.assertEquals(expected, output);
    }

    @Test
    public void testSuccessfulOutputOnPerfectGame() {
        final String playerName = "Herman";
        final GameDTO game = this.buildPinFallsForPerfectGame(playerName);
        final String output = this.toTest.buildOutput(game);

        final String expected =
                Constants.PRINT__PINFALLS_LABEL +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__PINFALLS_STRIKE_INDICATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__PINFALLS_STRIKE_INDICATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__PINFALLS_STRIKE_INDICATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__PINFALLS_STRIKE_INDICATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__PINFALLS_STRIKE_INDICATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__PINFALLS_STRIKE_INDICATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__PINFALLS_STRIKE_INDICATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__PINFALLS_STRIKE_INDICATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__PINFALLS_STRIKE_INDICATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__PINFALLS_STRIKE_INDICATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__PINFALLS_STRIKE_INDICATOR +
                        Constants.PRINT__FIELD_SEPARATOR +
                        Constants.PRINT__PINFALLS_STRIKE_INDICATOR;

        Assert.assertEquals(expected, output);
    }
}