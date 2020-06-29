package com.marcelomnc.score10pinbowling.parser;

import com.marcelomnc.score10pinbowling.common.Constants;
import com.marcelomnc.score10pinbowling.dto.PlayerChanceDTO;
import org.junit.Assert;
import org.junit.Test;

/**
 * This test class must be in the same package of the class being tested
 * because it has package level access, is a class not meant to be used publicly
 */
public class PlayerChancesFileLineParserTest {
    private PlayerChancesFileLineParser parser;

    public PlayerChancesFileLineParserTest() {
        this.parser = new PlayerChancesFileLineParser();
    }

    @Test
    public void shouldParseRegularLine() {
        String line = "Jeff" + Constants.PLAYER_CHANCES_FILE__FIELD_SEPARATOR + "9";
        PlayerChanceDTO playerChanceDTO = this.parser.parseLine(line);
        Assert.assertEquals(playerChanceDTO.getPlayerName(), "Jeff");
        Assert.assertEquals(playerChanceDTO.getKnockedDownPins(), 9);
    }

    @Test
    public void shouldParseFoulLine() {
        String line = "Jeff" + Constants.PLAYER_CHANCES_FILE__FIELD_SEPARATOR + Constants.PRINT__PINFALLS_FOUL_INDICATOR;
        PlayerChanceDTO playerChanceDTO = this.parser.parseLine(line);
        Assert.assertEquals(playerChanceDTO.getPlayerName(), "Jeff");
        Assert.assertEquals(playerChanceDTO.getKnockedDownPins(), 0);
        Assert.assertTrue(playerChanceDTO.isFoul());
    }
}
