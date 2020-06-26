package com.marcelomnc.score10pinbowling.dto;

import java.util.ArrayList;
import java.util.List;

public class FrameDTO {
    private List<PinFallDTO> pinFalls;
    private int score;

    public FrameDTO() {
        //Generally pinFalls size must be 2, except for the last frame (frame 10) in which its size must be 3
        this.pinFalls = new ArrayList<>(2);
    }

    public List<PinFallDTO> getPinFalls() {
        return pinFalls;
    }

    public void setPinFalls(List<PinFallDTO> pinFalls) {
        this.pinFalls = pinFalls;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
