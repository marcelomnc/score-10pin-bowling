package com.marcelomnc.score10pinbowling.common;

public class Constants {
    public static final String PLAYER_CHANCES_FILE__FIELD_SEPARATOR = "\t";
    public static final String PLAYER_CHANCES_FILE__FOUL_INDICATOR = "F";
    public static final String PRINT__GAMES_TITLE = "GAMES";
    public static final String PRINT__PLAYER_LABEL = "Player: ";
    public static final String PRINT__ELLIPSIS_LABEL = "...";
    public static final String PRINT__FRAME_SEPARATOR = "|";
    public static final String PRINT__FIELD_SEPARATOR = "\t";
    public static final String PRINT__FRAME_LABEL = "Frame";
    public static final String PRINT__PINFALLS_LABEL = "PinFalls";
    public static final String PRINT__PINFALLS_CHANCE_SKIPPED_INDICATOR = " ";
    public static final String PRINT__PINFALLS_FOUL_INDICATOR = "F";
    public static final String PRINT__PINFALLS_SPARE_INDICATOR = "/";
    public static final String PRINT__PINFALLS_STRIKE_INDICATOR = "X";
    public static final String PRINT__SCORE_LABEL = "Score";
    public static final int PRINT__FIRST_COLUMN_MAX_CHARS = 15;
    public static final int PRINT__FRAME_MAX_CHARS = 5;
    public static final int PRINT__LINE_MAX_CHARS = 76;

    public static final String PCFL_VALIDATOR__NO_FIELD_SEPARATOR_MESSAGE = "Line has no field separator";
    public static final String PCFL_VALIDATOR__NO_2_FIELDS_ONLY_MESSAGE = "Line has not 2 fields only";
    public static final String PCFL_VALIDATOR__FIELD_VALUE_0_10_MESSAGE = "Line has a value for 'knocked down pins' field that is not between 0 and 10";
    public static final String PCFL_VALIDATOR__FIELD_VALUE_NOT_NUMBER_FOUL_MESSAGE = "Line has a value for 'knocked down pins' field that is not a number neither an F";

    public static final String PCFLEP_PRINT__ERRORS_TITLE = "Player Chances File Errors Encountered:";
    public static final String PCFLEP_PRINT__ERRORS_LINE_LABEL = "Line: ";
    public static final String PCFLEP_PRINT__ERRORS_LINE_NUMBER_LABEL = "Line Number: ";
    public static final String PCFLEP_PRINT__ERRORS_LINE_ERROR_LABEL = "Error: ";
}