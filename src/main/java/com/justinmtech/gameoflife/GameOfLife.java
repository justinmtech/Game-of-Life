package com.justinmtech.gameoflife;

public class GameOfLife {

    public static void main(String[] args) {
        Environment environment = new Environment(800, 800, 500);
        GUI gui = new GUI(environment);
        gui.setFrameRate(50);
        gui.run();
    }
}
