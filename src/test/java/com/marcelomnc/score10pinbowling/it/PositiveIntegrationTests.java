package com.marcelomnc.score10pinbowling.it;

import com.marcelomnc.score10pinbowling.dto.FrameDTO;
import com.marcelomnc.score10pinbowling.dto.GameDTO;
import com.marcelomnc.score10pinbowling.dto.PlayerChancesFileParserResultDTO;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;

public class PositiveIntegrationTests extends BaseIntegrationTests {

    @Test
    public void testAllFoulsGame() throws IOException {
        final String testFilePath = "positive/all-fouls.txt";
        final String carl = "Carl";

        final PlayerChancesFileParserResultDTO playerChancesFileParserResult = this.playerChancesFileParser.parsePlayerChancesFile(this.loadInputTestFile(testFilePath));
        Assert.assertEquals(1, playerChancesFileParserResult.getGames().size());

        Assert.assertEquals(carl, playerChancesFileParserResult.getGames().get(carl).getPlayerName());

        final GameDTO carlGame = playerChancesFileParserResult.getGames().get(carl);
        this.gamesProcessor.processGames(Collections.singletonList(carlGame));

        Assert.assertTrue(carlGame.isValid());
        Assert.assertNull(carlGame.getInvalidationMessage());
        Assert.assertEquals(carl, carlGame.getPlayerName());
        Assert.assertEquals(10, carlGame.getFrames().size());

        //For all frames
        IntStream
                .range(0, 9)
                .forEach(index -> {
                    final FrameDTO frame = carlGame.getFrames().get(index);
                    Assert.assertEquals(2, frame.getPinFalls().size());
                    Assert.assertEquals(0, frame.getPinFalls().get(0).getKnockedDownPins());
                    Assert.assertEquals(0, frame.getPinFalls().get(1).getKnockedDownPins());
                    Assert.assertEquals(0, frame.getScore());
                    Assert.assertTrue(frame.getPinFalls().get(0).isFoul());
                });
    }

    @Test
    public void testAllZeroesGame() throws IOException {
        final String testFilePath = "positive/all-zeroes.txt";
        final String carl = "Carl";

        final PlayerChancesFileParserResultDTO playerChancesFileParserResult = this.playerChancesFileParser.parsePlayerChancesFile(this.loadInputTestFile(testFilePath));
        Assert.assertEquals(1, playerChancesFileParserResult.getGames().size());

        Assert.assertEquals(carl, playerChancesFileParserResult.getGames().get(carl).getPlayerName());

        final GameDTO carlGame = playerChancesFileParserResult.getGames().get(carl);
        this.gamesProcessor.processGames(Collections.singletonList(carlGame));

        Assert.assertTrue(carlGame.isValid());
        Assert.assertNull(carlGame.getInvalidationMessage());
        Assert.assertEquals(carl, carlGame.getPlayerName());
        Assert.assertEquals(10, carlGame.getFrames().size());

        //For all frames
        IntStream
                .range(0, 10)
                .forEach(index -> {
                    final FrameDTO frame = carlGame.getFrames().get(index);
                    Assert.assertEquals(2, frame.getPinFalls().size());
                    Assert.assertEquals(0, frame.getPinFalls().get(0).getKnockedDownPins());
                    Assert.assertEquals(0, frame.getPinFalls().get(1).getKnockedDownPins());
                    Assert.assertEquals(0, frame.getScore());
                    Assert.assertFalse(frame.getPinFalls().get(0).isFoul());
                });
    }

    @Test
    public void testPerfectGame() throws IOException {
        final String testFilePath = "positive/perfect.txt";
        final String carl = "Carl";

        final PlayerChancesFileParserResultDTO playerChancesFileParserResult = this.playerChancesFileParser.parsePlayerChancesFile(this.loadInputTestFile(testFilePath));
        Assert.assertEquals(1, playerChancesFileParserResult.getGames().size());

        Assert.assertEquals(carl, playerChancesFileParserResult.getGames().get(carl).getPlayerName());

        final GameDTO carlGame = playerChancesFileParserResult.getGames().get(carl);
        this.gamesProcessor.processGames(Collections.singletonList(carlGame));

        Assert.assertTrue(carlGame.isValid());
        Assert.assertNull(carlGame.getInvalidationMessage());
        Assert.assertEquals(carl, carlGame.getPlayerName());
        Assert.assertEquals(10, carlGame.getFrames().size());

        //For the first 9 frames
        IntStream
                .range(0, 9)
                .forEach(index -> {
                    final FrameDTO frame = carlGame.getFrames().get(index);
                    Assert.assertEquals(1, frame.getPinFalls().size());
                    Assert.assertEquals(10, frame.getPinFalls().get(0).getKnockedDownPins());
                    Assert.assertFalse(frame.getPinFalls().get(0).isFoul());
                });

        //For the last frame (10th)
        Assert.assertEquals(3, carlGame.getFrames().get(9).getPinFalls().size());
        carlGame.getFrames()
                .get(9)
                .getPinFalls()
                .forEach(pinFallDTO -> {
                    Assert.assertEquals(10, pinFallDTO.getKnockedDownPins());
                    Assert.assertFalse(pinFallDTO.isFoul());
                });

        //Score per frame
        Assert.assertEquals(30, carlGame.getFrames().get(0).getScore());
        Assert.assertEquals(60, carlGame.getFrames().get(1).getScore());
        Assert.assertEquals(90, carlGame.getFrames().get(2).getScore());
        Assert.assertEquals(120, carlGame.getFrames().get(3).getScore());
        Assert.assertEquals(150, carlGame.getFrames().get(4).getScore());
        Assert.assertEquals(180, carlGame.getFrames().get(5).getScore());
        Assert.assertEquals(210, carlGame.getFrames().get(6).getScore());
        Assert.assertEquals(240, carlGame.getFrames().get(7).getScore());
        Assert.assertEquals(270, carlGame.getFrames().get(8).getScore());
        Assert.assertEquals(300, carlGame.getFrames().get(9).getScore());
    }

    @Test
    public void test2GamesWithScores() throws IOException {
        final String testFilePath = "positive/scores.txt";
        final String jeff = "Jeff";
        final String john = "John";
        final PlayerChancesFileParserResultDTO playerChancesFileParserResult = this.playerChancesFileParser.parsePlayerChancesFile(this.loadInputTestFile(testFilePath));

        Assert.assertEquals(2, playerChancesFileParserResult.getGames().size());

        //Order must be maintained according to how the output appears in the input file
        Assert.assertEquals(jeff, ((GameDTO) playerChancesFileParserResult.getGames().values().toArray()[0]).getPlayerName());
        Assert.assertEquals(john, ((GameDTO) playerChancesFileParserResult.getGames().values().toArray()[1]).getPlayerName());

        this.gamesProcessor.processGames(new ArrayList<>(playerChancesFileParserResult.getGames().values()));

        final GameDTO jeffGame = playerChancesFileParserResult.getGames().get(jeff);
        final GameDTO johnGame = playerChancesFileParserResult.getGames().get(john);

        //Assertions for Jeff's game
        Assert.assertTrue(jeffGame.isValid());
        Assert.assertNull(jeffGame.getInvalidationMessage());
        Assert.assertEquals(10, jeffGame.getFrames().size());

        FrameDTO frame1 = jeffGame.getFrames().get(0);
        FrameDTO frame2 = jeffGame.getFrames().get(1);
        FrameDTO frame3 = jeffGame.getFrames().get(2);
        FrameDTO frame4 = jeffGame.getFrames().get(3);
        FrameDTO frame5 = jeffGame.getFrames().get(4);
        FrameDTO frame6 = jeffGame.getFrames().get(5);
        FrameDTO frame7 = jeffGame.getFrames().get(6);
        FrameDTO frame8 = jeffGame.getFrames().get(7);
        FrameDTO frame9 = jeffGame.getFrames().get(8);
        FrameDTO frame10 = jeffGame.getFrames().get(9);

        Assert.assertEquals(1, frame1.getPinFalls().size());
        Assert.assertFalse(frame1.getPinFalls().get(0).isFoul());
        Assert.assertEquals(10, frame1.getPinFalls().get(0).getKnockedDownPins());
        Assert.assertEquals(20, frame1.getScore());

        Assert.assertEquals(2, frame2.getPinFalls().size());
        Assert.assertFalse(frame2.getPinFalls().get(0).isFoul());
        Assert.assertFalse(frame2.getPinFalls().get(1).isFoul());
        Assert.assertEquals(7, frame2.getPinFalls().get(0).getKnockedDownPins());
        Assert.assertEquals(3, frame2.getPinFalls().get(1).getKnockedDownPins());
        Assert.assertEquals(39, frame2.getScore());

        Assert.assertEquals(2, frame3.getPinFalls().size());
        Assert.assertFalse(frame3.getPinFalls().get(0).isFoul());
        Assert.assertFalse(frame3.getPinFalls().get(1).isFoul());
        Assert.assertEquals(9, frame3.getPinFalls().get(0).getKnockedDownPins());
        Assert.assertEquals(0, frame3.getPinFalls().get(1).getKnockedDownPins());
        Assert.assertEquals(48, frame3.getScore());

        Assert.assertEquals(1, frame4.getPinFalls().size());
        Assert.assertFalse(frame4.getPinFalls().get(0).isFoul());
        Assert.assertEquals(10, frame4.getPinFalls().get(0).getKnockedDownPins());
        Assert.assertEquals(66, frame4.getScore());

        Assert.assertEquals(2, frame5.getPinFalls().size());
        Assert.assertFalse(frame5.getPinFalls().get(0).isFoul());
        Assert.assertFalse(frame5.getPinFalls().get(1).isFoul());
        Assert.assertEquals(0, frame5.getPinFalls().get(0).getKnockedDownPins());
        Assert.assertEquals(8, frame5.getPinFalls().get(1).getKnockedDownPins());
        Assert.assertEquals(74, frame5.getScore());

        Assert.assertEquals(2, frame6.getPinFalls().size());
        Assert.assertFalse(frame6.getPinFalls().get(0).isFoul());
        Assert.assertFalse(frame6.getPinFalls().get(1).isFoul());
        Assert.assertEquals(8, frame6.getPinFalls().get(0).getKnockedDownPins());
        Assert.assertEquals(2, frame6.getPinFalls().get(1).getKnockedDownPins());
        Assert.assertEquals(84, frame6.getScore());

        Assert.assertEquals(2, frame7.getPinFalls().size());
        Assert.assertTrue(frame7.getPinFalls().get(0).isFoul());
        Assert.assertFalse(frame7.getPinFalls().get(1).isFoul());
        Assert.assertEquals(0, frame7.getPinFalls().get(0).getKnockedDownPins());
        Assert.assertEquals(6, frame7.getPinFalls().get(1).getKnockedDownPins());
        Assert.assertEquals(90, frame7.getScore());

        Assert.assertEquals(1, frame8.getPinFalls().size());
        Assert.assertFalse(frame8.getPinFalls().get(0).isFoul());
        Assert.assertEquals(10, frame8.getPinFalls().get(0).getKnockedDownPins());
        Assert.assertEquals(120, frame8.getScore());

        Assert.assertEquals(1, frame9.getPinFalls().size());
        Assert.assertFalse(frame9.getPinFalls().get(0).isFoul());
        Assert.assertEquals(10, frame9.getPinFalls().get(0).getKnockedDownPins());
        Assert.assertEquals(148, frame9.getScore());

        Assert.assertEquals(3, frame10.getPinFalls().size());
        Assert.assertFalse(frame10.getPinFalls().get(0).isFoul());
        Assert.assertFalse(frame10.getPinFalls().get(1).isFoul());
        Assert.assertFalse(frame10.getPinFalls().get(2).isFoul());
        Assert.assertEquals(10, frame10.getPinFalls().get(0).getKnockedDownPins());
        Assert.assertEquals(8, frame10.getPinFalls().get(1).getKnockedDownPins());
        Assert.assertEquals(1, frame10.getPinFalls().get(2).getKnockedDownPins());
        Assert.assertEquals(167, frame10.getScore());

        //Assertions for John's game
        Assert.assertTrue(johnGame.isValid());
        Assert.assertNull(johnGame.getInvalidationMessage());
        Assert.assertEquals(10, johnGame.getFrames().size());

        frame1 = johnGame.getFrames().get(0);
        frame2 = johnGame.getFrames().get(1);
        frame3 = johnGame.getFrames().get(2);
        frame4 = johnGame.getFrames().get(3);
        frame5 = johnGame.getFrames().get(4);
        frame6 = johnGame.getFrames().get(5);
        frame7 = johnGame.getFrames().get(6);
        frame8 = johnGame.getFrames().get(7);
        frame9 = johnGame.getFrames().get(8);
        frame10 = johnGame.getFrames().get(9);

        Assert.assertEquals(2, frame1.getPinFalls().size());
        Assert.assertFalse(frame1.getPinFalls().get(0).isFoul());
        Assert.assertFalse(frame1.getPinFalls().get(1).isFoul());
        Assert.assertEquals(3, frame1.getPinFalls().get(0).getKnockedDownPins());
        Assert.assertEquals(7, frame1.getPinFalls().get(1).getKnockedDownPins());
        Assert.assertEquals(16, frame1.getScore());

        Assert.assertEquals(2, frame2.getPinFalls().size());
        Assert.assertFalse(frame2.getPinFalls().get(0).isFoul());
        Assert.assertFalse(frame2.getPinFalls().get(1).isFoul());
        Assert.assertEquals(6, frame2.getPinFalls().get(0).getKnockedDownPins());
        Assert.assertEquals(3, frame2.getPinFalls().get(1).getKnockedDownPins());
        Assert.assertEquals(25, frame2.getScore());

        Assert.assertEquals(1, frame3.getPinFalls().size());
        Assert.assertFalse(frame3.getPinFalls().get(0).isFoul());
        Assert.assertEquals(10, frame3.getPinFalls().get(0).getKnockedDownPins());
        Assert.assertEquals(44, frame3.getScore());

        Assert.assertEquals(2, frame4.getPinFalls().size());
        Assert.assertFalse(frame4.getPinFalls().get(0).isFoul());
        Assert.assertFalse(frame4.getPinFalls().get(1).isFoul());
        Assert.assertEquals(8, frame4.getPinFalls().get(0).getKnockedDownPins());
        Assert.assertEquals(1, frame4.getPinFalls().get(1).getKnockedDownPins());
        Assert.assertEquals(53, frame4.getScore());

        Assert.assertEquals(1, frame5.getPinFalls().size());
        Assert.assertFalse(frame5.getPinFalls().get(0).isFoul());
        Assert.assertEquals(10, frame5.getPinFalls().get(0).getKnockedDownPins());
        Assert.assertEquals(82, frame5.getScore());

        Assert.assertEquals(1, frame6.getPinFalls().size());
        Assert.assertFalse(frame6.getPinFalls().get(0).isFoul());
        Assert.assertEquals(10, frame6.getPinFalls().get(0).getKnockedDownPins());
        Assert.assertEquals(101, frame6.getScore());

        Assert.assertEquals(2, frame7.getPinFalls().size());
        Assert.assertFalse(frame7.getPinFalls().get(0).isFoul());
        Assert.assertFalse(frame7.getPinFalls().get(1).isFoul());
        Assert.assertEquals(9, frame7.getPinFalls().get(0).getKnockedDownPins());
        Assert.assertEquals(0, frame7.getPinFalls().get(1).getKnockedDownPins());
        Assert.assertEquals(110, frame7.getScore());

        Assert.assertEquals(2, frame8.getPinFalls().size());
        Assert.assertFalse(frame8.getPinFalls().get(0).isFoul());
        Assert.assertFalse(frame8.getPinFalls().get(1).isFoul());
        Assert.assertEquals(7, frame8.getPinFalls().get(0).getKnockedDownPins());
        Assert.assertEquals(3, frame8.getPinFalls().get(1).getKnockedDownPins());
        Assert.assertEquals(124, frame8.getScore());

        Assert.assertEquals(2, frame9.getPinFalls().size());
        Assert.assertFalse(frame9.getPinFalls().get(0).isFoul());
        Assert.assertFalse(frame9.getPinFalls().get(1).isFoul());
        Assert.assertEquals(4, frame9.getPinFalls().get(0).getKnockedDownPins());
        Assert.assertEquals(4, frame9.getPinFalls().get(1).getKnockedDownPins());
        Assert.assertEquals(132, frame9.getScore());

        Assert.assertEquals(3, frame10.getPinFalls().size());
        Assert.assertFalse(frame10.getPinFalls().get(0).isFoul());
        Assert.assertFalse(frame10.getPinFalls().get(1).isFoul());
        Assert.assertFalse(frame10.getPinFalls().get(2).isFoul());
        Assert.assertEquals(10, frame10.getPinFalls().get(0).getKnockedDownPins());
        Assert.assertEquals(9, frame10.getPinFalls().get(1).getKnockedDownPins());
        Assert.assertEquals(0, frame10.getPinFalls().get(2).getKnockedDownPins());
        Assert.assertEquals(151, frame10.getScore());
    }
}
