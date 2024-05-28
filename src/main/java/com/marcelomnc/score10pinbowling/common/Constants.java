package com.marcelomnc.score10pinbowling.common;

public class Constants {
    public static final String PLAYER_CHANCES_FILE__FIELD_SEPARATOR = "\t";
    public static final String PLAYER_CHANCES_FILE__FOUL_INDICATOR = "F";
    public static final String PRINT__FIELD_SEPARATOR = "\t";
    public static final String PRINT__FRAME_LABEL = "Frame";
    public static final String PRINT__PINFALLS_LABEL = "Pinfalls";
    public static final String PRINT__PINFALLS_FOUL_INDICATOR = "F";
    public static final String PRINT__PINFALLS_SPARE_INDICATOR = "/";
    public static final String PRINT__PINFALLS_STRIKE_INDICATOR = "X";
    public static final String PRINT__SCORE_LABEL = "Score";

    public static final String MAIN__IO_EXCEPTION_MESSAGE_PATTERN = "An exception occurred while trying to read the player chances file on path: [%s]. Application execution aborted.";
    public static final String CLA_VALIDATOR__ARGS_INVALID_MESSAGE = "This application expects just one argument (the input file path) in the command line, no more, no less.";
    public static final String PCFL_VALIDATOR__FILE_DOES_NOT_EXISTS_MESSAGE_PATTERN = "File in path [%s] not found. Application execution aborted.";
    public static final String PCFL_VALIDATOR__FILE_IS_EMPTY_MESSAGE_PATTERN = "File in path [%s] is empty. Application execution aborted.";
    public static final String PCFL_VALIDATOR__NO_FIELD_SEPARATOR_MESSAGE = "Line has no field separator.";
    public static final String PCFL_VALIDATOR__2_FIELDS_ONLY_MESSAGE = "Line must have 2 fields only, no more no less.";
    public static final String PCFL_VALIDATOR__FIELD_VALUE_0_10_MESSAGE = "The value for 'knocked down pins' field must be a value between 0 and 10. Found value: [%s].";
    public static final String PCFL_VALIDATOR__FIELD_VALUE_NOT_NUMBER_NOR_FOUL_MESSAGE = "The value for 'knocked down pins' field must be a number (0-10) or an F to indicate a foul. Found value: [%s].";
    public static final String PCFL_VALIDATOR__FRAME_EXCEEDS_MAX_SUM_MESSAGE_PATTERN = "Frame %s: Exceeds pin falls max sum of 10. Calculated sum: [%s].";
    public static final String PCFL_VALIDATOR__MAX_CHANCES_EXCEEDED_MESSAGE_PATTERN = "Frame %s: : Max player chances (scores/throws) exceeded.";
    public static final String PCFL_VALIDATOR__NOT_ENOUGH_DATA_MESSAGE = "There are not enough player chances (scores/throws) data in the input file to build the game's information completely.";

    public static final String PCFLEP_PRINT__ERRORS_TITLE = "Player Chances File Errors Encountered:";
    public static final String PCFLEP_PRINT__ERRORS_LINE_NUMBER_LABEL = "Line Number: ";
    public static final String PCFLEP_PRINT__ERRORS_ORIGINAL_LINE_LABEL = "Original line: ";
    public static final String PCFLEP_PRINT__ERRORS_LINE_ERROR_LABEL = "Error: ";

    public static final String GP_PRINT__NO_VALID_GAMES_TO_PRINT_MESSAGE = "No valid games to print.";
    public static final String GP_PRINT__INVALID_GAMES_TITLE = "Invalid games: ";
    public static final String GP_PRINT__INVALIDATION_MESSAGE_LABEL = "Invalidation message: ";
}
