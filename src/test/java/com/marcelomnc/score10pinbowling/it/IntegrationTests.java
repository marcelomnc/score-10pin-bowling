package com.marcelomnc.score10pinbowling.it;

import com.marcelomnc.score10pinbowling.common.AssertionsHelper;
import com.marcelomnc.score10pinbowling.dto.GameDTO;
import com.marcelomnc.score10pinbowling.parser.IPlayerChancesFileParser;
import com.marcelomnc.score10pinbowling.parser.PlayerChancesFileParser;
import com.marcelomnc.score10pinbowling.processor.IPinFallsProcessor;
import com.marcelomnc.score10pinbowling.processor.IScoresProcessor;
import com.marcelomnc.score10pinbowling.processor.PinFallsProcessor;
import com.marcelomnc.score10pinbowling.processor.ScoresProcessor;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertFalse;

public class IntegrationTests {
    private static final String PLAYER_CHANCES_INTEGRATION_TESTS_FILE_PATH = "src/test/resources/playerChancesFileForIntegrationTests.txt";
    private static Map<String, GameDTO> games;

    @BeforeClass
    public static void setUp() {
        //Read and parse the player chances file
        IPlayerChancesFileParser playerChancesFileParser = new PlayerChancesFileParser();
        Map<String, GameDTO> games = playerChancesFileParser.parsePlayerChancesFile(PLAYER_CHANCES_INTEGRATION_TESTS_FILE_PATH);
        //Process pinFalls
        IPinFallsProcessor pinFallsProcessor = new PinFallsProcessor();
        pinFallsProcessor.processPinFalls(games);
        //Process scores
        IScoresProcessor scoresProcessor = new ScoresProcessor();
        scoresProcessor.processScores(games);
        IntegrationTests.games = games;
    }

    @Test
    public void lackOfChancesGameShouldBeInvalid() {
        GameDTO gameDTO = games.get("Lack Of Chances");
        assertFalse(gameDTO.isValid());
    }

    @Test
    public void chancesExcessGameShouldBeInvalid() {
        GameDTO gameDTO = games.get("Chances Excess");
        assertFalse(gameDTO.isValid());
    }

    @Test
    public void allZeroesGamePinFallsShouldBeCorrect() {
        GameDTO gameDTO = games.get("Zeroes");
        AssertionsHelper.allZeroesGamePinFallsAssertions(gameDTO);
    }

    @Test
    public void allZeroesGameScoresShouldBeCorrect() {
        GameDTO gameDTO = games.get("Zeroes");
        AssertionsHelper.allZeroesGameScoresAssertions(gameDTO);
    }

    @Test
    public void allFoulsGamePinFallsShouldBeCorrect() {
        GameDTO gameDTO = games.get("Fouls");
        AssertionsHelper.allFoulsGamePinFallsAssertions(gameDTO);
    }

    @Test
    public void allFoulsGameScoresShouldBeCorrect() {
        GameDTO gameDTO = games.get("Fouls");
        AssertionsHelper.allFoulsGameScoresAssertions(gameDTO);
    }

    @Test
    public void perfectGamePinFallsShouldBeCorrect() {
        GameDTO gameDTO = games.get("Perfect");
        AssertionsHelper.perfectGamePinFallsAssertions(gameDTO);
    }

    @Test
    public void perfectGameScoresShouldBeCorrect() {
        GameDTO gameDTO = games.get("Perfect");
        AssertionsHelper.perfectGameScoresAssertions(gameDTO);
    }

    @Test
    public void jeffGamePinFallsShouldBeCorrect() {
        GameDTO gameDTO = games.get("Jeff");
        AssertionsHelper.jeffGamePinFallsAssertions(gameDTO);
    }

    @Test
    public void jeffGameScoresShouldBeCorrect() {
        GameDTO gameDTO = games.get("Jeff");
        AssertionsHelper.jeffGameScoresAssertions(gameDTO);
    }

    @Test
    public void johnGamePinFallsShouldBeCorrect() {
        GameDTO gameDTO = games.get("John");
        AssertionsHelper.johnGamePinFallsAssertions(gameDTO);
    }

    @Test
    public void johnGameScoresShouldBeCorrect() {
        GameDTO gameDTO = games.get("John");
        AssertionsHelper.johnGameScoresAssertions(gameDTO);
    }
}
