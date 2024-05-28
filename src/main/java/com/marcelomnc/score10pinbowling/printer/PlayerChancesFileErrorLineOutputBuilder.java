package com.marcelomnc.score10pinbowling.printer;

import com.marcelomnc.score10pinbowling.common.Constants;
import com.marcelomnc.score10pinbowling.dto.PlayerChancesFileErrorLineDTO;

public class PlayerChancesFileErrorLineOutputBuilder implements IPlayerChancesFileErrorLineOutputBuilder {
    @Override
    public String buildOutput(PlayerChancesFileErrorLineDTO errorLine) {
        final StringBuilder sb = new StringBuilder();

        if (!errorLine.isGeneral()) {
            //These are only for errors occurred in specific lines inside the input file
            sb.append(Constants.PCFLEP_PRINT__ERRORS_LINE_NUMBER_LABEL);
            sb.append(errorLine.getLineNumber());
            sb.append(System.lineSeparator());
            sb.append(Constants.PCFLEP_PRINT__ERRORS_ORIGINAL_LINE_LABEL);
            sb.append(errorLine.getOriginalLine());
            sb.append(System.lineSeparator());
        }

        sb.append(Constants.PCFLEP_PRINT__ERRORS_LINE_ERROR_LABEL);
        sb.append(errorLine.getErrorMessage());

        return sb.toString();
    }
}
