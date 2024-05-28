package com.marcelomnc.score10pinbowling.parser;

import com.marcelomnc.score10pinbowling.common.BaseTest;
import com.marcelomnc.score10pinbowling.common.Constants;
import com.marcelomnc.score10pinbowling.dto.PlayerChancesFileParserResultDTO;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class PlayerChancesFileParserTest extends BaseTest {
    private final IPlayerChancesFileParser toTest = new PlayerChancesFileParser();

    @Test
    public void testGeneralErrorWhenInputFileDoesNotExist() throws IOException {
        final String testFilePath = "not-real-path/not-exist.txt";
        final PlayerChancesFileParserResultDTO playerChancesFileParserResult = this.toTest.parsePlayerChancesFile(this.loadInputTestFile(testFilePath));

        Assert.assertEquals(1, playerChancesFileParserResult.getErrorLines().size());
        Assert.assertTrue(playerChancesFileParserResult.getErrorLines().get(0).isGeneral());
        Assert.assertEquals(
                String.format(Constants.PCFL_VALIDATOR__FILE_DOES_NOT_EXISTS_MESSAGE_PATTERN,
                        PLAYER_CHANCES_TESTS_FILE_PATH + testFilePath),
                playerChancesFileParserResult.getErrorLines().get(0).getErrorMessage());
    }

    @Test
    public void testGeneralErrorWhenInputFileIsEmpty() throws IOException {
        final String testFilePath = "negative/empty.txt";
        final PlayerChancesFileParserResultDTO playerChancesFileParserResult = this.toTest.parsePlayerChancesFile(this.loadInputTestFile(testFilePath));

        Assert.assertEquals(1, playerChancesFileParserResult.getErrorLines().size());
        Assert.assertTrue(playerChancesFileParserResult.getErrorLines().get(0).isGeneral());
        Assert.assertEquals(
                String.format(Constants.PCFL_VALIDATOR__FILE_IS_EMPTY_MESSAGE_PATTERN,
                        PLAYER_CHANCES_TESTS_FILE_PATH + testFilePath),
                playerChancesFileParserResult.getErrorLines().get(0).getErrorMessage());
    }

    @Test
    public void testParsingErrorOnInvalidScore() throws IOException {
        final String testFilePath = "negative/invalid-score.txt";
        final PlayerChancesFileParserResultDTO playerChancesFileParserResult = this.toTest.parsePlayerChancesFile(this.loadInputTestFile(testFilePath));

        Assert.assertEquals(1, playerChancesFileParserResult.getErrorLines().size());
        Assert.assertEquals(2, playerChancesFileParserResult.getErrorLines().get(0).getLineNumber());
        Assert.assertEquals(String.format(Constants.PCFL_VALIDATOR__FIELD_VALUE_NOT_NUMBER_NOR_FOUL_MESSAGE, "lorem"), playerChancesFileParserResult.getErrorLines().get(0).getErrorMessage());
    }

    @Test
    public void testParsingErrorOnNegativeScore() throws IOException {
        final String testFilePath = "negative/negative.txt";
        final PlayerChancesFileParserResultDTO playerChancesFileParserResult = this.toTest.parsePlayerChancesFile(this.loadInputTestFile(testFilePath));

        Assert.assertEquals(1, playerChancesFileParserResult.getErrorLines().size());
        Assert.assertEquals(2, playerChancesFileParserResult.getErrorLines().get(0).getLineNumber());
        Assert.assertEquals(String.format(Constants.PCFL_VALIDATOR__FIELD_VALUE_0_10_MESSAGE, -5), playerChancesFileParserResult.getErrorLines().get(0).getErrorMessage());
    }

    @Test
    public void testParsingErrorOnFreeText() throws IOException {
        final String testFilePath = "negative/free-text.txt";
        final PlayerChancesFileParserResultDTO playerChancesFileParserResult = this.toTest.parsePlayerChancesFile(this.loadInputTestFile(testFilePath));

        Assert.assertEquals(3, playerChancesFileParserResult.getErrorLines().size());
        Assert.assertEquals(1, playerChancesFileParserResult.getErrorLines().get(0).getLineNumber());
        Assert.assertEquals(Constants.PCFL_VALIDATOR__NO_FIELD_SEPARATOR_MESSAGE, playerChancesFileParserResult.getErrorLines().get(0).getErrorMessage());
        Assert.assertEquals(2, playerChancesFileParserResult.getErrorLines().get(1).getLineNumber());
        Assert.assertEquals(Constants.PCFL_VALIDATOR__NO_FIELD_SEPARATOR_MESSAGE, playerChancesFileParserResult.getErrorLines().get(1).getErrorMessage());
        Assert.assertEquals(3, playerChancesFileParserResult.getErrorLines().get(2).getLineNumber());
        Assert.assertEquals(Constants.PCFL_VALIDATOR__NO_FIELD_SEPARATOR_MESSAGE, playerChancesFileParserResult.getErrorLines().get(2).getErrorMessage());
    }
}