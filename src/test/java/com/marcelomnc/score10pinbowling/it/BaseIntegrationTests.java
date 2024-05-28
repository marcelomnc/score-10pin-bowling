package com.marcelomnc.score10pinbowling.it;

import com.marcelomnc.score10pinbowling.common.BaseTest;
import com.marcelomnc.score10pinbowling.parser.IPlayerChancesFileParser;
import com.marcelomnc.score10pinbowling.parser.PlayerChancesFileParser;
import com.marcelomnc.score10pinbowling.processor.GamesProcessor;

public class BaseIntegrationTests extends BaseTest {
    protected final IPlayerChancesFileParser playerChancesFileParser;
    protected final GamesProcessor gamesProcessor;

    protected BaseIntegrationTests() {
        this.playerChancesFileParser = new PlayerChancesFileParser();
        this.gamesProcessor = new GamesProcessor();
    }
}
