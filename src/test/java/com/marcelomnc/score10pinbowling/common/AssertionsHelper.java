package com.marcelomnc.score10pinbowling.common;

import com.marcelomnc.score10pinbowling.dto.GameDTO;
import org.junit.Assert;

public class AssertionsHelper {
    private static void allZeroesPinFallsAssertions(GameDTO gameDTO) {
        Assert.assertEquals(gameDTO.getFrameDTOS().get(0).getPinFalls().size(), 2);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(0).getPinFalls().get(0).getValue(), 0);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(0).getPinFalls().get(1).getValue(), 0);

        Assert.assertEquals(gameDTO.getFrameDTOS().get(1).getPinFalls().size(), 2);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(1).getPinFalls().get(0).getValue(), 0);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(1).getPinFalls().get(1).getValue(), 0);

        Assert.assertEquals(gameDTO.getFrameDTOS().get(2).getPinFalls().size(), 2);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(2).getPinFalls().get(0).getValue(), 0);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(2).getPinFalls().get(1).getValue(), 0);

        Assert.assertEquals(gameDTO.getFrameDTOS().get(3).getPinFalls().size(), 2);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(3).getPinFalls().get(0).getValue(), 0);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(3).getPinFalls().get(1).getValue(), 0);

        Assert.assertEquals(gameDTO.getFrameDTOS().get(4).getPinFalls().size(), 2);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(4).getPinFalls().get(0).getValue(), 0);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(4).getPinFalls().get(1).getValue(), 0);

        Assert.assertEquals(gameDTO.getFrameDTOS().get(5).getPinFalls().size(), 2);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(5).getPinFalls().get(0).getValue(), 0);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(5).getPinFalls().get(1).getValue(), 0);

        Assert.assertEquals(gameDTO.getFrameDTOS().get(6).getPinFalls().size(), 2);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(6).getPinFalls().get(0).getValue(), 0);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(6).getPinFalls().get(1).getValue(), 0);

        Assert.assertEquals(gameDTO.getFrameDTOS().get(7).getPinFalls().size(), 2);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(7).getPinFalls().get(0).getValue(), 0);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(7).getPinFalls().get(1).getValue(), 0);

        Assert.assertEquals(gameDTO.getFrameDTOS().get(8).getPinFalls().size(), 2);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(8).getPinFalls().get(0).getValue(), 0);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(8).getPinFalls().get(1).getValue(), 0);

        //No extra chances on frame 10
        Assert.assertEquals(gameDTO.getFrameDTOS().get(9).getPinFalls().size(), 2);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(9).getPinFalls().get(0).getValue(), 0);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(9).getPinFalls().get(1).getValue(), 0);
    }

    public static void allZeroesGamePinFallsAssertions(GameDTO gameDTO) {
        allZeroesPinFallsAssertions(gameDTO);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(0).getPinFalls().get(0).isFoul());
        Assert.assertFalse(gameDTO.getFrameDTOS().get(0).getPinFalls().get(1).isFoul());

        Assert.assertFalse(gameDTO.getFrameDTOS().get(1).getPinFalls().get(0).isFoul());
        Assert.assertFalse(gameDTO.getFrameDTOS().get(1).getPinFalls().get(1).isFoul());

        Assert.assertFalse(gameDTO.getFrameDTOS().get(2).getPinFalls().get(0).isFoul());
        Assert.assertFalse(gameDTO.getFrameDTOS().get(2).getPinFalls().get(1).isFoul());

        Assert.assertFalse(gameDTO.getFrameDTOS().get(3).getPinFalls().get(0).isFoul());
        Assert.assertFalse(gameDTO.getFrameDTOS().get(3).getPinFalls().get(1).isFoul());

        Assert.assertFalse(gameDTO.getFrameDTOS().get(4).getPinFalls().get(0).isFoul());
        Assert.assertFalse(gameDTO.getFrameDTOS().get(4).getPinFalls().get(1).isFoul());

        Assert.assertFalse(gameDTO.getFrameDTOS().get(5).getPinFalls().get(0).isFoul());
        Assert.assertFalse(gameDTO.getFrameDTOS().get(5).getPinFalls().get(1).isFoul());

        Assert.assertFalse(gameDTO.getFrameDTOS().get(6).getPinFalls().get(0).isFoul());
        Assert.assertFalse(gameDTO.getFrameDTOS().get(6).getPinFalls().get(1).isFoul());

        Assert.assertFalse(gameDTO.getFrameDTOS().get(7).getPinFalls().get(0).isFoul());
        Assert.assertFalse(gameDTO.getFrameDTOS().get(7).getPinFalls().get(1).isFoul());

        Assert.assertFalse(gameDTO.getFrameDTOS().get(8).getPinFalls().get(0).isFoul());
        Assert.assertFalse(gameDTO.getFrameDTOS().get(8).getPinFalls().get(1).isFoul());

        Assert.assertFalse(gameDTO.getFrameDTOS().get(9).getPinFalls().get(0).isFoul());
        Assert.assertFalse(gameDTO.getFrameDTOS().get(9).getPinFalls().get(1).isFoul());

    }

    public static void allZeroesGameScoresAssertions(GameDTO gameDTO) {
        Assert.assertEquals(gameDTO.getFrameDTOS().get(0).getScore(), 0);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(1).getScore(), 0);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(2).getScore(), 0);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(3).getScore(), 0);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(4).getScore(), 0);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(5).getScore(), 0);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(6).getScore(), 0);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(7).getScore(), 0);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(8).getScore(), 0);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(9).getScore(), 0);
    }

    public static void allFoulsGamePinFallsAssertions(GameDTO gameDTO) {
        allZeroesPinFallsAssertions(gameDTO);
        Assert.assertTrue(gameDTO.getFrameDTOS().get(0).getPinFalls().get(0).isFoul());
        Assert.assertTrue(gameDTO.getFrameDTOS().get(0).getPinFalls().get(1).isFoul());

        Assert.assertTrue(gameDTO.getFrameDTOS().get(1).getPinFalls().get(0).isFoul());
        Assert.assertTrue(gameDTO.getFrameDTOS().get(1).getPinFalls().get(1).isFoul());

        Assert.assertTrue(gameDTO.getFrameDTOS().get(2).getPinFalls().get(0).isFoul());
        Assert.assertTrue(gameDTO.getFrameDTOS().get(2).getPinFalls().get(1).isFoul());

        Assert.assertTrue(gameDTO.getFrameDTOS().get(3).getPinFalls().get(0).isFoul());
        Assert.assertTrue(gameDTO.getFrameDTOS().get(3).getPinFalls().get(1).isFoul());

        Assert.assertTrue(gameDTO.getFrameDTOS().get(4).getPinFalls().get(0).isFoul());
        Assert.assertTrue(gameDTO.getFrameDTOS().get(4).getPinFalls().get(1).isFoul());

        Assert.assertTrue(gameDTO.getFrameDTOS().get(5).getPinFalls().get(0).isFoul());
        Assert.assertTrue(gameDTO.getFrameDTOS().get(5).getPinFalls().get(1).isFoul());

        Assert.assertTrue(gameDTO.getFrameDTOS().get(6).getPinFalls().get(0).isFoul());
        Assert.assertTrue(gameDTO.getFrameDTOS().get(6).getPinFalls().get(1).isFoul());

        Assert.assertTrue(gameDTO.getFrameDTOS().get(7).getPinFalls().get(0).isFoul());
        Assert.assertTrue(gameDTO.getFrameDTOS().get(7).getPinFalls().get(1).isFoul());

        Assert.assertTrue(gameDTO.getFrameDTOS().get(8).getPinFalls().get(0).isFoul());
        Assert.assertTrue(gameDTO.getFrameDTOS().get(8).getPinFalls().get(1).isFoul());

        Assert.assertTrue(gameDTO.getFrameDTOS().get(9).getPinFalls().get(0).isFoul());
        Assert.assertTrue(gameDTO.getFrameDTOS().get(9).getPinFalls().get(1).isFoul());
    }

    public static void allFoulsGameScoresAssertions(GameDTO gameDTO) {
        allZeroesGameScoresAssertions(gameDTO);
    }

    public static void perfectGamePinFallsAssertions(GameDTO gameDTO) {
        //Frames 1 to 9 should have only 1 chance
        Assert.assertEquals(gameDTO.getFrameDTOS().get(0).getPinFalls().size(), 1);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(0).getPinFalls().get(0).getValue(), 10);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(0).getPinFalls().get(0).isFoul());

        Assert.assertEquals(gameDTO.getFrameDTOS().get(1).getPinFalls().size(), 1);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(1).getPinFalls().get(0).getValue(), 10);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(1).getPinFalls().get(0).isFoul());

        Assert.assertEquals(gameDTO.getFrameDTOS().get(2).getPinFalls().size(), 1);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(2).getPinFalls().get(0).getValue(), 10);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(2).getPinFalls().get(0).isFoul());

        Assert.assertEquals(gameDTO.getFrameDTOS().get(3).getPinFalls().size(), 1);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(3).getPinFalls().get(0).getValue(), 10);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(3).getPinFalls().get(0).isFoul());

        Assert.assertEquals(gameDTO.getFrameDTOS().get(4).getPinFalls().size(), 1);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(4).getPinFalls().get(0).getValue(), 10);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(4).getPinFalls().get(0).isFoul());

        Assert.assertEquals(gameDTO.getFrameDTOS().get(5).getPinFalls().size(), 1);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(5).getPinFalls().get(0).getValue(), 10);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(5).getPinFalls().get(0).isFoul());

        Assert.assertEquals(gameDTO.getFrameDTOS().get(6).getPinFalls().size(), 1);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(6).getPinFalls().get(0).getValue(), 10);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(6).getPinFalls().get(0).isFoul());

        Assert.assertEquals(gameDTO.getFrameDTOS().get(7).getPinFalls().size(), 1);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(7).getPinFalls().get(0).getValue(), 10);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(7).getPinFalls().get(0).isFoul());

        Assert.assertEquals(gameDTO.getFrameDTOS().get(8).getPinFalls().size(), 1);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(8).getPinFalls().get(0).getValue(), 10);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(8).getPinFalls().get(0).isFoul());

        //Frame 10 should have 3 chances
        Assert.assertEquals(gameDTO.getFrameDTOS().get(9).getPinFalls().size(), 3);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(9).getPinFalls().get(0).getValue(), 10);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(9).getPinFalls().get(0).isFoul());
        Assert.assertEquals(gameDTO.getFrameDTOS().get(9).getPinFalls().get(1).getValue(), 10);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(9).getPinFalls().get(1).isFoul());
        Assert.assertEquals(gameDTO.getFrameDTOS().get(9).getPinFalls().get(2).getValue(), 10);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(9).getPinFalls().get(2).isFoul());
    }

    public static void perfectGameScoresAssertions(GameDTO gameDTO) {
        Assert.assertEquals(gameDTO.getFrameDTOS().get(0).getScore(), 30);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(1).getScore(), 60);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(2).getScore(), 90);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(3).getScore(), 120);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(4).getScore(), 150);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(5).getScore(), 180);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(6).getScore(), 210);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(7).getScore(), 240);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(8).getScore(), 270);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(9).getScore(), 300);
    }

    public static void jeffGamePinFallsAssertions(GameDTO gameDTO) {
        Assert.assertEquals(gameDTO.getFrameDTOS().get(0).getPinFalls().size(), 1);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(0).getPinFalls().get(0).getValue(), 10);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(0).getPinFalls().get(0).isFoul());

        Assert.assertEquals(gameDTO.getFrameDTOS().get(1).getPinFalls().size(), 2);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(1).getPinFalls().get(0).getValue(), 7);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(1).getPinFalls().get(0).isFoul());
        Assert.assertEquals(gameDTO.getFrameDTOS().get(1).getPinFalls().get(1).getValue(), 3);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(1).getPinFalls().get(1).isFoul());

        Assert.assertEquals(gameDTO.getFrameDTOS().get(2).getPinFalls().size(), 2);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(2).getPinFalls().get(0).getValue(), 9);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(2).getPinFalls().get(0).isFoul());
        Assert.assertEquals(gameDTO.getFrameDTOS().get(2).getPinFalls().get(1).getValue(), 0);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(2).getPinFalls().get(1).isFoul());

        Assert.assertEquals(gameDTO.getFrameDTOS().get(3).getPinFalls().size(), 1);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(3).getPinFalls().get(0).getValue(), 10);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(3).getPinFalls().get(0).isFoul());

        Assert.assertEquals(gameDTO.getFrameDTOS().get(4).getPinFalls().size(), 2);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(4).getPinFalls().get(0).getValue(), 0);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(4).getPinFalls().get(0).isFoul());
        Assert.assertEquals(gameDTO.getFrameDTOS().get(4).getPinFalls().get(1).getValue(), 8);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(4).getPinFalls().get(1).isFoul());

        Assert.assertEquals(gameDTO.getFrameDTOS().get(5).getPinFalls().size(), 2);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(5).getPinFalls().get(0).getValue(), 8);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(5).getPinFalls().get(0).isFoul());
        Assert.assertEquals(gameDTO.getFrameDTOS().get(5).getPinFalls().get(1).getValue(), 2);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(5).getPinFalls().get(1).isFoul());

        Assert.assertEquals(gameDTO.getFrameDTOS().get(6).getPinFalls().size(), 2);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(6).getPinFalls().get(0).getValue(), 0);
        Assert.assertTrue(gameDTO.getFrameDTOS().get(6).getPinFalls().get(0).isFoul());
        Assert.assertEquals(gameDTO.getFrameDTOS().get(6).getPinFalls().get(1).getValue(), 6);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(6).getPinFalls().get(1).isFoul());

        Assert.assertEquals(gameDTO.getFrameDTOS().get(7).getPinFalls().size(), 1);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(7).getPinFalls().get(0).getValue(), 10);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(7).getPinFalls().get(0).isFoul());

        Assert.assertEquals(gameDTO.getFrameDTOS().get(8).getPinFalls().size(), 1);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(8).getPinFalls().get(0).getValue(), 10);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(8).getPinFalls().get(0).isFoul());

        //3 chances on frame 10
        Assert.assertEquals(gameDTO.getFrameDTOS().get(9).getPinFalls().size(), 3);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(9).getPinFalls().get(0).getValue(), 10);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(9).getPinFalls().get(0).isFoul());
        Assert.assertEquals(gameDTO.getFrameDTOS().get(9).getPinFalls().get(1).getValue(), 8);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(9).getPinFalls().get(1).isFoul());
        Assert.assertEquals(gameDTO.getFrameDTOS().get(9).getPinFalls().get(2).getValue(), 1);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(9).getPinFalls().get(2).isFoul());
    }

    public static void jeffGameScoresAssertions(GameDTO gameDTO) {
        Assert.assertEquals(gameDTO.getFrameDTOS().get(0).getScore(), 20);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(1).getScore(), 39);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(2).getScore(), 48);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(3).getScore(), 66);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(4).getScore(), 74);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(5).getScore(), 84);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(6).getScore(), 90);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(7).getScore(), 120);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(8).getScore(), 148);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(9).getScore(), 167);
    }

    public static void johnGamePinFallsAssertions(GameDTO gameDTO) {
        Assert.assertEquals(gameDTO.getFrameDTOS().get(0).getPinFalls().size(), 2);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(0).getPinFalls().get(0).getValue(), 3);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(0).getPinFalls().get(0).isFoul());
        Assert.assertEquals(gameDTO.getFrameDTOS().get(0).getPinFalls().get(1).getValue(), 7);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(0).getPinFalls().get(1).isFoul());

        Assert.assertEquals(gameDTO.getFrameDTOS().get(1).getPinFalls().size(), 2);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(1).getPinFalls().get(0).getValue(), 6);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(1).getPinFalls().get(0).isFoul());
        Assert.assertEquals(gameDTO.getFrameDTOS().get(1).getPinFalls().get(1).getValue(), 3);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(1).getPinFalls().get(1).isFoul());

        Assert.assertEquals(gameDTO.getFrameDTOS().get(2).getPinFalls().size(), 1);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(2).getPinFalls().get(0).getValue(), 10);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(2).getPinFalls().get(0).isFoul());

        Assert.assertEquals(gameDTO.getFrameDTOS().get(3).getPinFalls().size(), 2);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(3).getPinFalls().get(0).getValue(), 8);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(3).getPinFalls().get(0).isFoul());
        Assert.assertEquals(gameDTO.getFrameDTOS().get(3).getPinFalls().get(1).getValue(), 1);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(3).getPinFalls().get(1).isFoul());

        Assert.assertEquals(gameDTO.getFrameDTOS().get(4).getPinFalls().size(), 1);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(4).getPinFalls().get(0).getValue(), 10);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(4).getPinFalls().get(0).isFoul());

        Assert.assertEquals(gameDTO.getFrameDTOS().get(5).getPinFalls().size(), 1);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(5).getPinFalls().get(0).getValue(), 10);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(5).getPinFalls().get(0).isFoul());

        Assert.assertEquals(gameDTO.getFrameDTOS().get(6).getPinFalls().size(), 2);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(6).getPinFalls().get(0).getValue(), 9);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(6).getPinFalls().get(0).isFoul());
        Assert.assertEquals(gameDTO.getFrameDTOS().get(6).getPinFalls().get(1).getValue(), 0);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(6).getPinFalls().get(1).isFoul());

        Assert.assertEquals(gameDTO.getFrameDTOS().get(7).getPinFalls().size(), 2);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(7).getPinFalls().get(0).getValue(), 7);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(7).getPinFalls().get(0).isFoul());
        Assert.assertEquals(gameDTO.getFrameDTOS().get(7).getPinFalls().get(1).getValue(), 3);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(7).getPinFalls().get(1).isFoul());

        Assert.assertEquals(gameDTO.getFrameDTOS().get(8).getPinFalls().size(), 2);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(8).getPinFalls().get(0).getValue(), 4);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(8).getPinFalls().get(0).isFoul());
        Assert.assertEquals(gameDTO.getFrameDTOS().get(8).getPinFalls().get(1).getValue(), 4);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(8).getPinFalls().get(1).isFoul());

        //3 chances on frame 10
        Assert.assertEquals(gameDTO.getFrameDTOS().get(9).getPinFalls().size(), 3);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(9).getPinFalls().get(0).getValue(), 10);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(9).getPinFalls().get(0).isFoul());
        Assert.assertEquals(gameDTO.getFrameDTOS().get(9).getPinFalls().get(1).getValue(), 9);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(9).getPinFalls().get(1).isFoul());
        Assert.assertEquals(gameDTO.getFrameDTOS().get(9).getPinFalls().get(2).getValue(), 0);
        Assert.assertFalse(gameDTO.getFrameDTOS().get(9).getPinFalls().get(2).isFoul());
    }

    public static void johnGameScoresAssertions(GameDTO gameDTO) {
        Assert.assertEquals(gameDTO.getFrameDTOS().get(0).getScore(), 16);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(1).getScore(), 25);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(2).getScore(), 44);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(3).getScore(), 53);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(4).getScore(), 82);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(5).getScore(), 101);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(6).getScore(), 110);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(7).getScore(), 124);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(8).getScore(), 132);
        Assert.assertEquals(gameDTO.getFrameDTOS().get(9).getScore(), 151);
    }
}
