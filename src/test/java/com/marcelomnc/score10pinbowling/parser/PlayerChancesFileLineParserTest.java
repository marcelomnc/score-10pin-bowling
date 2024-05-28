package com.marcelomnc.score10pinbowling.parser;

import com.marcelomnc.score10pinbowling.common.Constants;
import com.marcelomnc.score10pinbowling.dto.PlayerChanceDTO;
import org.junit.Assert;
import org.junit.Test;

public class PlayerChancesFileLineParserTest {

    private final IPlayerChancesFileLineParser toTest;

    public PlayerChancesFileLineParserTest() {
        this.toTest = new PlayerChancesFileLineParser();
    }

    @Test
    public void testParseLineWithScoreValue() {
        final String playerName = "Jeff";
        final int knockedDownPins = 10;
        final String line = playerName +
                Constants.PLAYER_CHANCES_FILE__FIELD_SEPARATOR +
                knockedDownPins;
        final PlayerChanceDTO playerChanceDTO = this.toTest.parseLine(line);
        Assert.assertEquals(playerName, playerChanceDTO.getPlayerName());
        Assert.assertEquals(knockedDownPins, playerChanceDTO.getKnockedDownPins());
    }

    @Test
    public void testParseLineWithScoreValueForFoul() {
        final String playerName = "Jeff";
        final String foulIndicator = Constants.PLAYER_CHANCES_FILE__FOUL_INDICATOR;
        final String line = playerName +
                Constants.PLAYER_CHANCES_FILE__FIELD_SEPARATOR +
                foulIndicator;
        final PlayerChanceDTO playerChanceDTO = this.toTest.parseLine(line);
        Assert.assertEquals(playerName, playerChanceDTO.getPlayerName());
        Assert.assertTrue(playerChanceDTO.isFoul());
    }
}
