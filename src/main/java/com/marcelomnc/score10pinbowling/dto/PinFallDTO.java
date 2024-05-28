package com.marcelomnc.score10pinbowling.dto;

import java.io.Serializable;

public class PinFallDTO implements Serializable {
    private final boolean foul;
    private final int knockedDownPins;

    public PinFallDTO(boolean foul, int knockedDownPins) {
        this.foul = foul;
        this.knockedDownPins = knockedDownPins;
    }

    public boolean isFoul() {
        return foul;
    }

    public int getKnockedDownPins() {
        return knockedDownPins;
    }
}
