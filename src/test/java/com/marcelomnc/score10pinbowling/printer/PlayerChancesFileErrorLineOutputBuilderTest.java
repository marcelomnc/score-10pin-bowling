package com.marcelomnc.score10pinbowling.printer;

import com.marcelomnc.score10pinbowling.common.Constants;
import com.marcelomnc.score10pinbowling.dto.PlayerChancesFileErrorLineDTO;
import org.junit.Assert;
import org.junit.Test;

public class PlayerChancesFileErrorLineOutputBuilderTest {
    private final PlayerChancesFileErrorLineOutputBuilder toTest = new PlayerChancesFileErrorLineOutputBuilder();

    @Test
    public void testCorrectOutputForGeneralError() {
        final String errorMessage = "A test error message";
        final PlayerChancesFileErrorLineDTO errorLine = new PlayerChancesFileErrorLineDTO("", -1, errorMessage);
        errorLine.setGeneral(true);
        final String output = this.toTest.buildOutput(errorLine);

        Assert.assertEquals(Constants.PCFLEP_PRINT__ERRORS_LINE_ERROR_LABEL + errorMessage, output);
    }

    @Test
    public void testCorrectOutputForNonGeneralError() {
        final String originalLine = "originalLine\tvalue";
        final int lineNumber = 200;
        final String errorMessage = "A test error message";
        final PlayerChancesFileErrorLineDTO errorLine = new PlayerChancesFileErrorLineDTO(originalLine, lineNumber, errorMessage);
        errorLine.setGeneral(false);
        final String output = this.toTest.buildOutput(errorLine);

        final String expected = Constants.PCFLEP_PRINT__ERRORS_LINE_NUMBER_LABEL +
                lineNumber +
                System.lineSeparator() +
                Constants.PCFLEP_PRINT__ERRORS_ORIGINAL_LINE_LABEL +
                originalLine +
                System.lineSeparator() +
                Constants.PCFLEP_PRINT__ERRORS_LINE_ERROR_LABEL +
                errorMessage;

        Assert.assertEquals(expected, output);
    }
}
