package com.marcelomnc.score10pinbowling.validator;

import com.marcelomnc.score10pinbowling.common.Constants;
import com.marcelomnc.score10pinbowling.dto.PlayerChancesFileErrorLineDTO;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class PlayerChancesFileLineValidatorTest {

    private final PlayerChancesFileLineValidator toTest;

    public PlayerChancesFileLineValidatorTest() {
        this.toTest = new PlayerChancesFileLineValidator();
    }

    @Test
    public void testLineWithNoFieldSeparator() {
        final int lineNumber = 50;
        final StringBuilder sb = new StringBuilder()
                .append("Jeff");
        final Optional<PlayerChancesFileErrorLineDTO> playerChancesFileErrorLineDTO = this.toTest.validate(sb.toString(), lineNumber);
        Assert.assertTrue(playerChancesFileErrorLineDTO.isPresent());
        Assert.assertEquals(sb.toString(), playerChancesFileErrorLineDTO.get().getOriginalLine());
        Assert.assertEquals(lineNumber, playerChancesFileErrorLineDTO.get().getLineNumber());
        Assert.assertEquals(Constants.PCFL_VALIDATOR__NO_FIELD_SEPARATOR_MESSAGE, playerChancesFileErrorLineDTO.get().getErrorMessage());
    }

    @Test
    public void testLineWithLessThanTwoFields() {
        final int lineNumber = 50;
        final StringBuilder sb = new StringBuilder()
                .append("Jeff")
                .append(Constants.PLAYER_CHANCES_FILE__FIELD_SEPARATOR);
        final Optional<PlayerChancesFileErrorLineDTO> playerChancesFileErrorLineDTO = this.toTest.validate(sb.toString(), lineNumber);
        Assert.assertTrue(playerChancesFileErrorLineDTO.isPresent());
        Assert.assertEquals(sb.toString(), playerChancesFileErrorLineDTO.get().getOriginalLine());
        Assert.assertEquals(lineNumber, playerChancesFileErrorLineDTO.get().getLineNumber());
        Assert.assertEquals(Constants.PCFL_VALIDATOR__2_FIELDS_ONLY_MESSAGE, playerChancesFileErrorLineDTO.get().getErrorMessage());
    }

    @Test
    public void testLineWithMoreThanTwoFields() {
        final int lineNumber = 100;
        final StringBuilder sb = new StringBuilder()
                .append("Jeff")
                .append(Constants.PLAYER_CHANCES_FILE__FIELD_SEPARATOR)
                .append(9)
                .append(Constants.PLAYER_CHANCES_FILE__FIELD_SEPARATOR)
                .append("dummy-field-value");
        final Optional<PlayerChancesFileErrorLineDTO> playerChancesFileErrorLineDTO = this.toTest.validate(sb.toString(), lineNumber);
        Assert.assertTrue(playerChancesFileErrorLineDTO.isPresent());
        Assert.assertEquals(sb.toString(), playerChancesFileErrorLineDTO.get().getOriginalLine());
        Assert.assertEquals(lineNumber, playerChancesFileErrorLineDTO.get().getLineNumber());
        Assert.assertEquals(Constants.PCFL_VALIDATOR__2_FIELDS_ONLY_MESSAGE, playerChancesFileErrorLineDTO.get().getErrorMessage());
    }

    @Test
    public void testLineWithScoreValueNotNumberNorFoul() {
        final int lineNumber = 150;
        final String invalidKnockedDownPinsValue = "M";
        final StringBuilder sb = new StringBuilder()
                .append("Jeff")
                .append(Constants.PLAYER_CHANCES_FILE__FIELD_SEPARATOR)
                .append(invalidKnockedDownPinsValue);
        final Optional<PlayerChancesFileErrorLineDTO> playerChancesFileErrorLineDTO = this.toTest.validate(sb.toString(), lineNumber);
        Assert.assertTrue(playerChancesFileErrorLineDTO.isPresent());
        Assert.assertEquals(sb.toString(), playerChancesFileErrorLineDTO.get().getOriginalLine());
        Assert.assertEquals(lineNumber, playerChancesFileErrorLineDTO.get().getLineNumber());
        Assert.assertEquals(String.format(Constants.PCFL_VALIDATOR__FIELD_VALUE_NOT_NUMBER_NOR_FOUL_MESSAGE, invalidKnockedDownPinsValue), playerChancesFileErrorLineDTO.get().getErrorMessage());
    }

    @Test
    public void testLineWithScoreValueLessThanZero() {
        final int lineNumber = 150;
        final int knockedDownPins = -1;
        final StringBuilder sb = new StringBuilder()
                .append("Jeff")
                .append(Constants.PLAYER_CHANCES_FILE__FIELD_SEPARATOR)
                .append(knockedDownPins);
        final Optional<PlayerChancesFileErrorLineDTO> playerChancesFileErrorLineDTO = this.toTest.validate(sb.toString(), lineNumber);
        Assert.assertTrue(playerChancesFileErrorLineDTO.isPresent());
        Assert.assertEquals(sb.toString(), playerChancesFileErrorLineDTO.get().getOriginalLine());
        Assert.assertEquals(lineNumber, playerChancesFileErrorLineDTO.get().getLineNumber());
        Assert.assertEquals(String.format(Constants.PCFL_VALIDATOR__FIELD_VALUE_0_10_MESSAGE, knockedDownPins), playerChancesFileErrorLineDTO.get().getErrorMessage());
    }

    @Test
    public void testLineWithScoreValueGreaterThanTen() {
        final int lineNumber = 150;
        final int knockedDownPins = 11;
        final StringBuilder sb = new StringBuilder()
                .append("Jeff")
                .append(Constants.PLAYER_CHANCES_FILE__FIELD_SEPARATOR)
                .append(knockedDownPins);
        final Optional<PlayerChancesFileErrorLineDTO> playerChancesFileErrorLineDTO = this.toTest.validate(sb.toString(), lineNumber);
        Assert.assertTrue(playerChancesFileErrorLineDTO.isPresent());
        Assert.assertEquals(sb.toString(), playerChancesFileErrorLineDTO.get().getOriginalLine());
        Assert.assertEquals(lineNumber, playerChancesFileErrorLineDTO.get().getLineNumber());
        Assert.assertEquals(String.format(Constants.PCFL_VALIDATOR__FIELD_VALUE_0_10_MESSAGE, knockedDownPins), playerChancesFileErrorLineDTO.get().getErrorMessage());
    }

    @Test
    public void testLineWithScoreValueValid() {
        final String line = "Jeff" +
                Constants.PLAYER_CHANCES_FILE__FIELD_SEPARATOR +
                9;
        Assert.assertFalse(this.toTest.validate(line, 1).isPresent());
    }

    @Test
    public void testLineWithScoreValueForFoul() {
        final String line = "Jeff" +
                Constants.PLAYER_CHANCES_FILE__FIELD_SEPARATOR +
                "F";
        Assert.assertFalse(this.toTest.validate(line, 1).isPresent());
    }
}
