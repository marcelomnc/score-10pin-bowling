package com.marcelomnc.score10pinbowling.printer;

import com.marcelomnc.score10pinbowling.dto.PlayerChancesFileErrorLineDTO;

public interface IPlayerChancesFileErrorLineOutputBuilder {
    String buildOutput(PlayerChancesFileErrorLineDTO errorLine);
}
