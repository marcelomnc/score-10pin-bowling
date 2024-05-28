package com.marcelomnc.score10pinbowling.dto;

import java.io.Serializable;

public class PlayerChancesFileErrorLineDTO implements Serializable {
    private final String originalLine;
    private final int lineNumber;
    private final String errorMessage;
    private boolean isGeneral;

    public PlayerChancesFileErrorLineDTO(String originalLine, int lineNumber, String errorMessage) {
        this.originalLine = originalLine;
        this.lineNumber = lineNumber;
        this.errorMessage = errorMessage;
    }

    public String getOriginalLine() {
        return originalLine;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isGeneral() {
        return isGeneral;
    }

    public void setGeneral(boolean general) {
        isGeneral = general;
    }
}
