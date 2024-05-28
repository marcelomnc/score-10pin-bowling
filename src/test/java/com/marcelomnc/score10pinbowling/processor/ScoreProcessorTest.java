package com.marcelomnc.score10pinbowling.processor;

import com.marcelomnc.score10pinbowling.dto.FrameDTO;
import com.marcelomnc.score10pinbowling.dto.GameDTO;
import com.marcelomnc.score10pinbowling.dto.PinFallDTO;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ScoreProcessorTest extends BaseProcessorTest {
    private final ScoreProcessor toTest = new ScoreProcessor();

    private GameDTO completeBuildAllFoulsGame(String playerName) {
        final GameDTO game = super.buildAllFoulsGame(playerName);
        final PinFallDTO foulPinFallDTO = new PinFallDTO(true, 0);

        //For all 10 frames
        final List<FrameDTO> frames = IntStream
                .range(0, 10)
                .mapToObj(value -> {
                    final FrameDTO regularFrameDTO = new FrameDTO();
                    regularFrameDTO.getPinFalls().add(foulPinFallDTO);
                    regularFrameDTO.getPinFalls().add(foulPinFallDTO);
                    return regularFrameDTO;
                }).collect(Collectors.toList());

        game.getFrames().addAll(frames);

        return game;
    }

    private GameDTO completeBuildAllZeroesGame(String playerName) {
        final GameDTO game = super.buildAllZeroesGame(playerName);
        final PinFallDTO zeroPinFallDTO = new PinFallDTO(false, 0);

        //For all 10 frames
        final List<FrameDTO> frames = IntStream
                .range(0, 10)
                .mapToObj(value -> {
                    final FrameDTO regularFrameDTO = new FrameDTO();
                    regularFrameDTO.getPinFalls().add(zeroPinFallDTO);
                    regularFrameDTO.getPinFalls().add(zeroPinFallDTO);
                    return regularFrameDTO;
                }).collect(Collectors.toList());

        game.getFrames().addAll(frames);

        return game;
    }

    private GameDTO completeBuildPerfectGame(String playerName) {
        final GameDTO game = super.buildPerfectGame(playerName);
        final PinFallDTO perfectPinFallDTO = new PinFallDTO(false, 10);

        //For the first 9 frames
        final List<FrameDTO> frames = IntStream
                .range(0, 9)
                .mapToObj(value -> {
                    final FrameDTO regularFrameDTO = new FrameDTO();
                    regularFrameDTO.getPinFalls().add(perfectPinFallDTO);
                    return regularFrameDTO;
                }).collect(Collectors.toList());

        //Last frame
        final FrameDTO lastFrameDTO = new FrameDTO();
        lastFrameDTO.getPinFalls().add(perfectPinFallDTO);
        lastFrameDTO.getPinFalls().add(perfectPinFallDTO);
        lastFrameDTO.getPinFalls().add(perfectPinFallDTO);
        frames.add(lastFrameDTO);

        game.getFrames().addAll(frames);

        return game;
    }

    @Test
    public void testSuccessfulProcessOnAllFoulsGame() {
        final String playerName = "Mark";
        final GameDTO game = this.completeBuildAllFoulsGame(playerName);
        this.toTest.processGame(game);
        Assert.assertEquals(playerName, game.getPlayerName());
        Assert.assertTrue(game.isValid());
        game.getFrames().forEach(frameDTO -> Assert.assertEquals(0, frameDTO.getScore()));
    }

    @Test
    public void testSuccessfulProcessOnAllZeroesGame() {
        final String playerName = "Tom";
        final GameDTO game = this.completeBuildAllZeroesGame(playerName);
        this.toTest.processGame(game);
        Assert.assertEquals(playerName, game.getPlayerName());
        Assert.assertTrue(game.isValid());
        game.getFrames().forEach(frameDTO -> Assert.assertEquals(0, frameDTO.getScore()));
    }

    @Test
    public void testSuccessfulProcessOnPerfectGame() {
        final String playerName = "Travis";
        final GameDTO game = this.completeBuildPerfectGame(playerName);
        this.toTest.processGame(game);

        Assert.assertEquals(playerName, game.getPlayerName());
        Assert.assertTrue(game.isValid());
        Assert.assertEquals(30, game.getFrames().get(0).getScore());
        Assert.assertEquals(60, game.getFrames().get(1).getScore());
        Assert.assertEquals(90, game.getFrames().get(2).getScore());
        Assert.assertEquals(120, game.getFrames().get(3).getScore());
        Assert.assertEquals(150, game.getFrames().get(4).getScore());
        Assert.assertEquals(180, game.getFrames().get(5).getScore());
        Assert.assertEquals(210, game.getFrames().get(6).getScore());
        Assert.assertEquals(240, game.getFrames().get(7).getScore());
        Assert.assertEquals(270, game.getFrames().get(8).getScore());
        Assert.assertEquals(300, game.getFrames().get(9).getScore());
    }
}