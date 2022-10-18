package com.justinmtech.gameoflife;

import java.util.ArrayList;
import java.util.List;

public class Environment {
    private final int height;
    private final int width;
    private int generation;
    private final int maxGeneration;
    private int[][] cells;
    private final List<int[][]> history;
    private final static String cellAlive = "*";
    private final static String cellDead = " ";

    public Environment(int height, int width, int generationDelay) {
        this.history = new ArrayList<>();
        this.height = height;
        this.width = width;
        this.generation = 1;
        this.maxGeneration = 250;
        this.cells = new int[width][height];
        initializeGame(generationDelay);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public List<int[][]> getHistory() {
        return history;
    }

    private void initializeGame(int generationDelay) {
        printGeneration(0);
        generateRandomCells();
        //displayEnvironment();
        loopGameOfLife(generationDelay);
    }

    private void printGeneration(int number) {
        System.out.println("Generation: " + number);
    }

    private void generateRandomCells() {
        int y = 0;
        while (y < getHeight()) {
            for (int x = 0; x < getWidth(); x++) {
                int r = getNumberBetween(0, 20);
                if (r == 1) setCellState(x, y, 1);
                else setCellState(x, y, 0);
            }
            y++;
        }
    }

    private int getNumberBetween(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private int getCell(int x, int y) {
        if ((x < 0 || x > getWidth() - 1) || (y < 0 || y > getHeight() - 1)) return 0;
        return cells[x][y];
    }

    private void displayEnvironment() {
        int y = 0;
        while (y < getHeight()) {
            for (int[] ints : getCells()) {
                int state = ints[y];
                printCellDisplay(state);
            }
            printEmptyLine();
            y++;
        }
    }

    private void printCellDisplay(int state) {
        System.out.print(getCellDisplay(state));
    }

    private void printEmptyLine() {
        System.out.println();
    }

    private int[] generateLineOfCellsAtY(int y) {
        int[] newLine = new int[getWidth()];
        int prev = y - 1;
        for (int x = 1; x < getWidth() - 1; x++) {
            newLine[x] = getLifeOrDeath(getCell(x, prev), getNeighborCells(prev, x));
        }
        return newLine;
    }

    private int[] getNeighborCells(int y, int x) {
        int topLeft = getCell(x - 1, y - 1);
        int up = getCell(x, y - 1);
        int topRight = getCell(x + 1, y - 1);
        int left = getCell(x - 1, y);
        int right = getCell(x + 1, y);
        int bottomLeft = getCell(x - 1, y + 1);
        int down = getCell(x, y + 1);
        int bottomRight = getCell(x + 1, y + 1);
        return new int[]{topLeft, up, topRight, left, right, bottomLeft, down, bottomRight};
    }

    private int[][] generateNextEnvironment() {
        int[][] newEnvironment = new int[getWidth()][getHeight()];
        for (int y = 1; y < getHeight(); y++) {
            int[] line = generateLineOfCellsAtY(y);
            for (int x = 0; x < getWidth(); x++) {
                newEnvironment[x][y - 1] = line[x];
            }
        }
        return newEnvironment;
    }

    private void loopGameOfLife(int delayInMilliseconds) {
        while (generation < maxGeneration) {
            int[][] newEnvironment = generateNextEnvironment();
            addHistory(getCells());
            copyNewGenerationToCurrent(newEnvironment);
            //printGeneration(getGeneration());
            //displayEnvironment();
            //runDelay(delayInMilliseconds);
            incrementGeneration();
        }
        printDoneMessage();
    }

    private void printDoneMessage() {
        System.out.println("Done!");
    }

    private void runDelay(int delayInMilliseconds) {
        try {
            Thread.sleep(delayInMilliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void copyNewGenerationToCurrent(int[][] newGeneration) {
        setCells(newGeneration);
    }

    public void setCells(int[][] cells) {
        this.cells = cells;
    }

    private int getLifeOrDeath(int cell, int[] neighbors) {
        int lifeCounter = 0; //1 = life, 0 = death
        for (int i : neighbors) if (i == 1) lifeCounter++;

        if (cell == 1 && lifeCounter < 2) return 0;
        else if (cell == 1 && lifeCounter < 4) return 1;
        else if (cell == 1 && lifeCounter > 3) return 0;
        else if (cell == 0 && lifeCounter == 3) return 1;
        return 0;
    }

    private String getCellDisplay(int state) {
        return state == 1 ? Environment.cellAlive : Environment.cellDead;
    }

    private int getGeneration() {
        return generation;
    }

    private void incrementGeneration() {
        this.generation++;
    }

    private void setCellState(int x, int y, int state) {
        this.cells[x][y] = state;
    }

    private void addHistory(int[][] environment) {
        this.history.add(environment);
    }

    private int[][] getCells() {
        return cells;
    }
}
