package com.marcelomnc.score10pinbowling.dto;

public class PlayerChancesFileLineError {
    private String line;
    private int lineNumber;
    private String errorMessage;

    public PlayerChancesFileLineError(String line, int lineNumber, String errorMessage) {
        this.line = line;
        this.lineNumber = lineNumber;
        this.errorMessage = errorMessage;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}