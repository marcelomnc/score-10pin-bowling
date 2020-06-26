package com.marcelomnc.score10pinbowling.dto;

public class PlayerChanceDTO {
    private String playerName;
    private int knockedDownPins;
    private boolean foul;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
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

    public void setFoul(boolean foul) {
        this.foul = foul;
    }
}
