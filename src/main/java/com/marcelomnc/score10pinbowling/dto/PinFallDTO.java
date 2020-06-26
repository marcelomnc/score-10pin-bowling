package com.marcelomnc.score10pinbowling.dto;

public class PinFallDTO {
    private boolean foul;
    private int value;

    public PinFallDTO() {
    }

    public PinFallDTO(boolean foul, int value) {
        this.foul = foul;
        this.value = value;
    }

    public boolean isFoul() {
        return foul;
    }

    public void setFoul(boolean foul) {
        this.foul = foul;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
