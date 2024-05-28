package com.marcelomnc.score10pinbowling.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FrameDTO implements Serializable {
    private final List<PinFallDTO> pinFalls;
    private int score;

    public FrameDTO() {
        this(0);
    }

    public FrameDTO(int score) {
        //Generally pinFalls size must be 2, except for the last frame (frame 10) in which its size must be 3
        this.pinFalls = new ArrayList<>(2);
        this.score = score;
    }

    public List<PinFallDTO> getPinFalls() {
        return pinFalls;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
