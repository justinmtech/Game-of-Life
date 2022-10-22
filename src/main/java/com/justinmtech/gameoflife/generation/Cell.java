package com.justinmtech.gameoflife.generation;

import java.awt.*;

public class Cell {
    private int state;
    private Color color;

    public Cell(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
