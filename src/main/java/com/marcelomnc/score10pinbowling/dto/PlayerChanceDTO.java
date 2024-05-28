package com.marcelomnc.score10pinbowling.dto;

import java.io.Serializable;

public class PlayerChanceDTO implements Serializable {
    private final String playerName;
    private int knockedDownPins;
    private final boolean foul;

    public PlayerChanceDTO(String playerName, int knockedDownPins, boolean foul) {
        this.playerName = playerName;
        this.knockedDownPins = knockedDownPins;
        this.foul = foul;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getKnockedDownPins() {
        return knockedDownPins;
    }

    public void setKnockedDownPins(int knockedDownPins) {
        this.knockedDownPins = knockedDownPins;
    }

    public boolean isFoul() {
        return foul;
    }
}
