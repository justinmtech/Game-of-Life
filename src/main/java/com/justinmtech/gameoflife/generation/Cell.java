package com.justinmtech.gameoflife.generation;

import java.awt.*;

//Cells will be accessed and iterated over with a Map
public class Cell {
    private int state;
    private Color color;
    private int[] coordinates;

    public Cell(int state, int x, int y) {
        this.state = state;
        this.coordinates = new int[]{x, y};
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

    public int[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(int x, int y) {
        this.coordinates[0] = x;
        this.coordinates[1] = y;
    }
}
