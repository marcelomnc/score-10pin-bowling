package com.marcelomnc.score10pinbowling.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameDTO implements Serializable {
    private final String playerName;
    private List<PlayerChanceDTO> playerChances;
    private final List<FrameDTO> frames;
    private boolean valid;
    private String invalidationMessage;

    public GameDTO(String playerName) {
        this.playerName = playerName;
        this.playerChances = new ArrayList<>();
        //10 frames per game
        this.frames = new ArrayList<>(10);
        this.valid = true;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<PlayerChanceDTO> getPlayerChances() {
        return playerChances;
    }

    public void setPlayerChances(List<PlayerChanceDTO> playerChances) {
        this.playerChances = playerChances;
    }

    public List<FrameDTO> getFrames() {
        return frames;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getInvalidationMessage() {
        return invalidationMessage;
    }

    public void setInvalidationMessage(String invalidationMessage) {
        this.invalidationMessage = invalidationMessage;
    }
}
