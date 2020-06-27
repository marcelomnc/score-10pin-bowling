package com.marcelomnc.score10pinbowling.dto;

import java.util.ArrayList;
import java.util.List;

public class GameDTO {
    private String playerName;
    private List<FrameDTO> frameDTOS;
    private List<PlayerChanceDTO> playerChanceDTOs;
    private boolean valid;

    public GameDTO(String playerName) {
        this.playerName = playerName;
        this.frameDTOS = new ArrayList<>(10);
        this.playerChanceDTOs = new ArrayList<>();
        this.valid = true;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public List<FrameDTO> getFrameDTOS() {
        return frameDTOS;
    }

    public void setFrameDTOS(List<FrameDTO> frameDTOS) {
        this.frameDTOS = frameDTOS;
    }

    public List<PlayerChanceDTO> getPlayerChanceDTOs() {
        return playerChanceDTOs;
    }

    public void setPlayerChanceDTOs(List<PlayerChanceDTO> playerChanceDTOs) {
        this.playerChanceDTOs = playerChanceDTOs;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
