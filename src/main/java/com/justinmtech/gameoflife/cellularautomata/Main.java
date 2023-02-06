package com.justinmtech.gameoflife.cellularautomata;

public class Main {

    public static void main(String[] args) {
        CellularAutomata ca = new CellularAutomata();
        Drawing draw = new Drawing(ca);
        draw.run(500, 500);
    }
}
