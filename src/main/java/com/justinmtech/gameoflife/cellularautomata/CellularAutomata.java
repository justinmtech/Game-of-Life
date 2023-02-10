package com.justinmtech.gameoflife.cellularautomata;

import com.justinmtech.gameoflife.config.GameConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CellularAutomata {
    private int generation;
    private int height;
    private int[] cells;
    private final List<int[]> cellHistory;
    private int width;
    private int[] seed;
    private GameConfig config;

    public CellularAutomata(GameConfig config) {
        generation = 1;
        this.height = config.getHeight();
        this.width = config.getWidth();
        this.cells = new int[width];
        this.seed = config.getSeed();
        Arrays.fill(cells, 0);
        this.cells[width/2] = 1;
        this.cellHistory = new ArrayList<>();
    }

    public void run() {
        cellHistory.add(cells);
        while (generation < height) {
            generate();
            //printCellsToConsole(cells);
            cellHistory.add(cells);
        }
    }

    private void printCellsToConsole(int[] gen) {
        for (int cell : gen) {
            System.out.print(getCellDisplay(cell));
        }
        System.out.println();
    }

    private String getCellDisplay(int value) {
        return value == 1 ? "⬛" : "☐";
    }

    private void generate() {
        int[] newGen = new int[width];
        for (int i = 1; i < cells.length - 1; i++) {
            int left = cells[i - 1];
            int me = cells[i];
            int right = cells[i + 1];
            newGen[i] = applyRules(left, me, right);
        }
        cells = newGen;
        generation++;
    }

    private int applyRules(int a, int b, int c) {
        if (a == 1 && b == 1 && c == 1) return seed[0];
        if (a == 1 && b == 1 && c == 0) return seed[1];
        if (a == 1 && b == 0 && c == 1) return seed[2];
        if (a == 1 && b == 0 && c == 0) return seed[3];
        if (a == 0 && b == 1 && c == 1) return seed[4];
        if (a == 0 && b == 1 && c == 0) return seed[5];
        if (a == 0 && b == 0 && c == 1) return seed[6];
        if (a == 0 && b == 0 && c == 0) return seed[7];
        return 0;
    }
    public List<int[]> getCellHistory() {
        return cellHistory;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int[] getCells() {
        return cells;
    }

    public void setCells(int[] cells) {
        this.cells = cells;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int[] getSeed() {
        return seed;
    }

    public void setSeed(int[] seed) {
        this.seed = seed;
    }

    public GameConfig getConfig() {
        return config;
    }

    public void setConfig(GameConfig config) {
        this.config = config;
    }
}
