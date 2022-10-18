package com.justinmtech.gameoflife;

public class GameOfLife {

    public static void main(String[] args) {
        Environment environment = new Environment(540, 960, 0);
        GUI gui = new GUI(environment);
        gui.main();
    }
}
