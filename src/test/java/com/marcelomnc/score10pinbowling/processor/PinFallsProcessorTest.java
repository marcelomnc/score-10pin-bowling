package com.marcelomnc.score10pinbowling.processor;

import com.marcelomnc.score10pinbowling.common.Constants;
import com.marcelomnc.score10pinbowling.dto.FrameDTO;
import com.marcelomnc.score10pinbowling.dto.GameDTO;
import com.marcelomnc.score10pinbowling.dto.PlayerChanceDTO;
import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;

public class PinFallsProcessorTest extends BaseProcessorTest {
    private final PinFallsProcessor toTest = new PinFallsProcessor();

    private GameDTO buildExtraScoreGame(String playerName) {
        final GameDTO game = this.buildPerfectGame(playerName);
        //Adding extra chance
        game.getPlayerChances().add(new PlayerChanceDTO(playerName, 10, false));
        return game;
    }

    private GameDTO buildLackOfScoreInRegularFrameGame(String playerName) {
        final GameDTO game = this.buildPerfectGame(playerName);
        game.setPlayerChances(game.getPlayerChances().subList(0, 4));
        return game;
    }

    private GameDTO buildLackOfScoreInLastFrameGame(String playerName) {
        final GameDTO game = this.buildPerfectGame(playerName);
        game.setPlayerChances(game.getPlayerChances().subList(0, 11));
        return game;
    }

    private GameDTO buildRegularFrameSumGreaterThan10Game(String playerName) {
        final GameDTO game = this.buildAllZeroesGame(playerName);
        game.getPlayerChances().get(2).setKnockedDownPins(9);
        game.getPlayerChances().get(3).setKnockedDownPins(9);
        return game;
    }

    private GameDTO buildLastFrameSumGreaterThan10Game(String playerName) {
        final GameDTO game = this.buildAllZeroesGame(playerName);
        game.getPlayerChances().get(18).setKnockedDownPins(9);
        game.getPlayerChances().get(19).setKnockedDownPins(9);
        return game;
    }

    @Test
    public void testSuccessfulProcessingForAllFoulsGame() {
        final String playerName = "Vincent";
        final GameDTO allFoulsGame = this.buildAllFoulsGame(playerName);
        this.toTest.processGame(allFoulsGame);

        Assert.assertEquals(playerName, allFoulsGame.getPlayerName());
        Assert.assertTrue(allFoulsGame.isValid());
        Assert.assertNull(allFoulsGame.getInvalidationMessage());
        Assert.assertNotNull(allFoulsGame.getFrames());
        Assert.assertFalse(allFoulsGame.getFrames().isEmpty());
        Assert.assertEquals(10, allFoulsGame.getFrames().size());

        allFoulsGame.getFrames().forEach(frame -> {
            Assert.assertEquals(2, frame.getPinFalls().size());
            Assert.assertEquals(0, frame.getPinFalls().get(0).getKnockedDownPins());
            Assert.assertTrue(frame.getPinFalls().get(0).isFoul());
        });
    }

    @Test
    public void testSuccessfulProcessingForAllZeroesGame() {
        final String playerName = "Peter";
        final GameDTO allZeroesGame = this.buildAllZeroesGame(playerName);
        this.toTest.processGame(allZeroesGame);

        Assert.assertEquals(playerName, allZeroesGame.getPlayerName());
        Assert.assertTrue(allZeroesGame.isValid());
        Assert.assertNull(allZeroesGame.getInvalidationMessage());
        Assert.assertNotNull(allZeroesGame.getFrames());
        Assert.assertFalse(allZeroesGame.getFrames().isEmpty());
        Assert.assertEquals(10, allZeroesGame.getFrames().size());

        allZeroesGame.getFrames().forEach(frame -> {
            Assert.assertEquals(2, frame.getPinFalls().size());
            Assert.assertEquals(0, frame.getPinFalls().get(0).getKnockedDownPins());
            Assert.assertFalse(frame.getPinFalls().get(0).isFoul());
        });
    }

    @Test
    public void testSuccessfulProcessingForPerfectGame() {
        final String playerName = "Marcus";
        final GameDTO perfectGame = this.buildPerfectGame(playerName);
        this.toTest.processGame(perfectGame);

        Assert.assertEquals(playerName, perfectGame.getPlayerName());
        Assert.assertTrue(perfectGame.isValid());
        Assert.assertNull(perfectGame.getInvalidationMessage());
        Assert.assertNotNull(perfectGame.getFrames());
        Assert.assertFalse(perfectGame.getFrames().isEmpty());
        Assert.assertEquals(10, perfectGame.getFrames().size());

        //For the first 9 frames
        IntStream
                .range(0, 8)
                .forEach(index -> {
                    final FrameDTO frame = perfectGame.getFrames().get(index);
                    Assert.assertEquals(1, frame.getPinFalls().size());
                    Assert.assertEquals(10, frame.getPinFalls().get(0).getKnockedDownPins());
                    Assert.assertFalse(frame.getPinFalls().get(0).isFoul());
                });

        //For the last frame (10th)
        Assert.assertEquals(3, perfectGame.getFrames().get(9).getPinFalls().size());
        perfectGame.getFrames()
                .get(9)
                .getPinFalls()
                .forEach(pinFall -> {
                    Assert.assertEquals(10, pinFall.getKnockedDownPins());
                    Assert.assertFalse(pinFall.isFoul());
                });
    }

    @Test
    public void testGameInvalidationOnExtraScore() {
        final String playerName = "Marcus";
        final GameDTO extraScoreGame = this.buildExtraScoreGame(playerName);
        this.toTest.processGame(extraScoreGame);

        Assert.assertEquals(playerName, extraScoreGame.getPlayerName());
        Assert.assertFalse(extraScoreGame.isValid());
        Assert.assertEquals(String.format(Constants.PCFL_VALIDATOR__MAX_CHANCES_EXCEEDED_MESSAGE_PATTERN, 10), extraScoreGame.getInvalidationMessage());
    }

    @Test
    public void testGameInvalidationOnLackOfScoresInRegularFrame() {
        final String playerName = "Steve";
        final GameDTO lackOfScoresInRegularFrameGame = this.buildLackOfScoreInRegularFrameGame(playerName);
        this.toTest.processGame(lackOfScoresInRegularFrameGame);

        Assert.assertEquals(playerName, lackOfScoresInRegularFrameGame.getPlayerName());
        Assert.assertFalse(lackOfScoresInRegularFrameGame.isValid());
        Assert.assertEquals(Constants.PCFL_VALIDATOR__NOT_ENOUGH_DATA_MESSAGE, lackOfScoresInRegularFrameGame.getInvalidationMessage());
    }

    @Test
    public void testGameInvalidationOnLackOfScoresInLastFrame() {
        final String playerName = "Steve";
        final GameDTO lackOfScoresInLastFrameGame = this.buildLackOfScoreInLastFrameGame(playerName);
        this.toTest.processGame(lackOfScoresInLastFrameGame);

        Assert.assertEquals(playerName, lackOfScoresInLastFrameGame.getPlayerName());
        Assert.assertFalse(lackOfScoresInLastFrameGame.isValid());
        Assert.assertEquals(Constants.PCFL_VALIDATOR__NOT_ENOUGH_DATA_MESSAGE, lackOfScoresInLastFrameGame.getInvalidationMessage());
    }

    @Test
    public void testInvalidateGameOnRegularFrameSumGreaterThan10() {
        final String playerName = "Steve";
        final GameDTO game = this.buildRegularFrameSumGreaterThan10Game(playerName);
        this.toTest.processGame(game);

        Assert.assertEquals(playerName, game.getPlayerName());
        Assert.assertFalse(game.isValid());
        Assert.assertEquals(String.format(Constants.PCFL_VALIDATOR__FRAME_EXCEEDS_MAX_SUM_MESSAGE_PATTERN, 2, 18), game.getInvalidationMessage());
    }

    @Test
    public void testInvalidateGameOnLastFrameSumGreaterThan10() {
        final String playerName = "Steve";
        final GameDTO game = this.buildLastFrameSumGreaterThan10Game(playerName);
        this.toTest.processGame(game);

        Assert.assertEquals(playerName, game.getPlayerName());
        Assert.assertFalse(game.isValid());
        Assert.assertEquals(String.format(Constants.PCFL_VALIDATOR__FRAME_EXCEEDS_MAX_SUM_MESSAGE_PATTERN, 10, 18), game.getInvalidationMessage());
    }
}