package com.justinmtech.gameoflife.cellularautomata;

import com.justinmtech.gameoflife.config.GameConfig;

public class Main {
    private static GameConfig config;

    public Main(GameConfig config) {
        Main.config = config;
    }

    public static void main(String[] args) {
        CellularAutomata ca = new CellularAutomata(config);
        Drawing draw = new Drawing(ca, ca.getConfig());
        draw.run(500, 500);
    }
}
