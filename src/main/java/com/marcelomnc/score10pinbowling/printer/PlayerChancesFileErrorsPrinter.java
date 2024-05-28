package com.marcelomnc.score10pinbowling.printer;

import com.marcelomnc.score10pinbowling.common.Constants;
import com.marcelomnc.score10pinbowling.dto.PlayerChancesFileErrorLineDTO;

import java.util.List;

public class PlayerChancesFileErrorsPrinter {
    private final IPlayerChancesFileErrorLineOutputBuilder playerChancesFileErrorLineOutputBuilder;

    public PlayerChancesFileErrorsPrinter() {
        this(new PlayerChancesFileErrorLineOutputBuilder());
    }

    PlayerChancesFileErrorsPrinter(IPlayerChancesFileErrorLineOutputBuilder playerChancesFileErrorLineOutputBuilder) {
        this.playerChancesFileErrorLineOutputBuilder = playerChancesFileErrorLineOutputBuilder;
    }

    public void printErrors(List<PlayerChancesFileErrorLineDTO> errorLines) {
        System.out.println(Constants.PCFLEP_PRINT__ERRORS_TITLE + System.lineSeparator());
        errorLines.forEach(errorLine -> System.out.println(this.playerChancesFileErrorLineOutputBuilder.buildOutput(errorLine) + System.lineSeparator()));
    }
}
