package com.marcelomnc.score10pinbowling.dto;

import java.util.ArrayList;
import java.util.List;

public class GameDTO {
    private String ownerName;
    private List<FrameDTO> frameDTOS;
    private List<PlayerChanceDTO> playerChanceDTOs;
    private boolean printable;

    public GameDTO(String ownerName) {
        this.ownerName = ownerName;
        this.frameDTOS = new ArrayList<>(10);
        this.playerChanceDTOs = new ArrayList<>();
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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

    public boolean isPrintable() {
        return printable;
    }

    public void setPrintable(boolean printable) {
        this.printable = printable;
    }
}
