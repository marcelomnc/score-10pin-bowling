package com.marcelomnc.score10pinbowling.it;

import com.marcelomnc.score10pinbowling.common.Constants;
import com.marcelomnc.score10pinbowling.dto.GameDTO;
import com.marcelomnc.score10pinbowling.dto.PlayerChancesFileParserResultDTO;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;

public class NegativeIntegrationTests extends BaseIntegrationTests {

    @Test
    public void testInvalidateGameOnExtraScore() throws IOException {
        final String testFilePath = "negative/extra-score.txt";
        final String carl = "Carl";
        final PlayerChancesFileParserResultDTO playerChancesFileParserResult = this.playerChancesFileParser.parsePlayerChancesFile(this.loadInputTestFile(testFilePath));
        final GameDTO game = (GameDTO) playerChancesFileParserResult.getGames().values().toArray()[0];
        this.gamesProcessor.processGames(Collections.singletonList(game));

        Assert.assertFalse(game.isValid());
        Assert.assertEquals(carl, game.getPlayerName());
        Assert.assertEquals(String.format(Constants.PCFL_VALIDATOR__MAX_CHANCES_EXCEEDED_MESSAGE_PATTERN, 10), game.getInvalidationMessage());
    }

    @Test
    public void testInvalidateGameOnRegularFrameSumGreaterThan10() throws IOException {
        final String testFilePath = "negative/regular-frame-sum-greater-than-10.txt";
        final String carl = "Carl";
        final PlayerChancesFileParserResultDTO playerChancesFileParserResult = this.playerChancesFileParser.parsePlayerChancesFile(this.loadInputTestFile(testFilePath));
        final GameDTO game = (GameDTO) playerChancesFileParserResult.getGames().values().toArray()[0];
        this.gamesProcessor.processGames(Collections.singletonList(game));

        Assert.assertFalse(game.isValid());
        Assert.assertEquals(carl, game.getPlayerName());
        Assert.assertEquals(String.format(Constants.PCFL_VALIDATOR__FRAME_EXCEEDS_MAX_SUM_MESSAGE_PATTERN, 3, 16), game.getInvalidationMessage());
    }

    @Test
    public void testInvalidateGameOnLastFrameSumGreaterThan10() throws IOException {
        final String testFilePath = "negative/last-frame-sum-greater-than-10.txt";
        final String carl = "Carl";
        final PlayerChancesFileParserResultDTO playerChancesFileParserResult = this.playerChancesFileParser.parsePlayerChancesFile(this.loadInputTestFile(testFilePath));
        final GameDTO game = (GameDTO) playerChancesFileParserResult.getGames().values().toArray()[0];
        this.gamesProcessor.processGames(Collections.singletonList(game));

        Assert.assertFalse(game.isValid());
        Assert.assertEquals(carl, game.getPlayerName());
        Assert.assertEquals(String.format(Constants.PCFL_VALIDATOR__FRAME_EXCEEDS_MAX_SUM_MESSAGE_PATTERN, 10, 18), game.getInvalidationMessage());
    }

    @Test
    public void testInvalidateGameOnLackOfScoresInRegularFrame() throws IOException {
        final String testFilePath = "negative/lack-of-scores-regular-frame.txt";
        final String leopold = "Leopold";
        final PlayerChancesFileParserResultDTO playerChancesFileParserResult = this.playerChancesFileParser.parsePlayerChancesFile(this.loadInputTestFile(testFilePath));
        final GameDTO game = (GameDTO) playerChancesFileParserResult.getGames().values().toArray()[0];
        this.gamesProcessor.processGames(Collections.singletonList(game));

        Assert.assertFalse(game.isValid());
        Assert.assertEquals(leopold, game.getPlayerName());
        Assert.assertEquals(Constants.PCFL_VALIDATOR__NOT_ENOUGH_DATA_MESSAGE, game.getInvalidationMessage());
    }

    @Test
    public void testInvalidateGameOnLackOfScoresInLastFrame() throws IOException {
        final String testFilePath = "negative/lack-of-scores-last-frame.txt";
        final String leopold = "Leopold";
        final PlayerChancesFileParserResultDTO playerChancesFileParserResult = this.playerChancesFileParser.parsePlayerChancesFile(this.loadInputTestFile(testFilePath));
        final GameDTO game = (GameDTO) playerChancesFileParserResult.getGames().values().toArray()[0];
        this.gamesProcessor.processGames(Collections.singletonList(game));

        Assert.assertFalse(game.isValid());
        Assert.assertEquals(leopold, game.getPlayerName());
        Assert.assertEquals(Constants.PCFL_VALIDATOR__NOT_ENOUGH_DATA_MESSAGE, game.getInvalidationMessage());
    }
}
