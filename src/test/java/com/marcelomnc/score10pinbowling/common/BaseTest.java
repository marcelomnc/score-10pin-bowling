package com.marcelomnc.score10pinbowling.common;

import java.io.File;

public class BaseTest {
    protected static final String PLAYER_CHANCES_TESTS_FILE_PATH = "src/test/resources/";

    protected File loadInputTestFile(String testFileSubPath) {
        return new File(PLAYER_CHANCES_TESTS_FILE_PATH + testFileSubPath);
    }
}
