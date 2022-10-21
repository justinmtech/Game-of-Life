package com.justinmtech.gameoflife;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Environment {
    private final GameConfig gameConfig;
    private int generation;
    private int[][] cells;
    private final List<int[][]> history;

    public Environment(ConfigManager configManager) {
        this.gameConfig = configManager.getGameConfig();
        this.history = new ArrayList<>();
        this.generation = 1;
        this.cells = new int[gameConfig.getWidth()][gameConfig.getHeight()];
    }

    public int getHeight() {
        return gameConfig.getHeight();
    }

    public int getWidth() {
        return gameConfig.getWidth();
    }

    public List<int[][]> getHistory() {
        return history;
    }

    private int[] getSeed() {
        return gameConfig.getSeed();
    }

    public void run(boolean seed) {
        if (seed && getSeed() != null) {
            System.out.println("[Game of Life] Now generating game with seed.. [" + Arrays.toString(getSeed()) + "]");
            generateCellsFromSeed();
        } else {
            System.out.println("[Game of Life] Now generating game with chance.." + " [cell chance: " + gameConfig.getGenerationChance() + "]");
            generateRandomCells();
        }
        loopGameOfLife();
    }

    public void generateRandomCells() {
        int y = 0;
        while (y < getHeight() - 1) {
            for (int x = 0; x < getWidth(); x++) {
                int max = getNumberBetween(1, gameConfig.getGenerationChance());
                int r = getNumberBetween(0, max);
                if (r == 1) setCellState(x, y, 1);
                else setCellState(x, y, 0);
            }
            y++;
        }
    }

    public void generateCellsFromSeed() {
        int y = 0;
        int seedLength = getSeed().length;
        int seedIndex = 0;
        while (y < getHeight() - 1) {
            for (int x = 0; x < getWidth(); x++) {
                setCellState(x, y, getSeed()[seedIndex]);
                if (seedIndex < seedLength - 1) {
                    seedIndex++;
                } else {
                    seedIndex = 0;
                }
            }
            y++;
        }
    }

    public static int getNumberBetween(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private int getCell(int x, int y) {
        if ((x < 0 || x > getWidth() - 1) || (y < 0 || y > getHeight() - 1)) return 0;
        return cells[x][y];
    }

    private int[] generateLineOfCellsAtY(int y) {
        int[] newLine = new int[getWidth()];
        int prev = y - 1;
        for (int x = 1; x < getWidth(); x++) {
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
        for (int y = 1; y < getHeight() - 1; y++) {
            int[] line = generateLineOfCellsAtY(y);
            for (int x = 0; x < getWidth(); x++) {
                newEnvironment[x][y - 1] = line[x];
            }
        }
        return newEnvironment;
    }

    private void printCellsToConsole() {
        int y = 0;
        printGeneration();
        while (y < getHeight() - 1) {
            for (int x = 0; x < getWidth(); x++) {
                printIndividualCell(getCell(x, y));
            }
            printEmptyLine();
            y++;
        }
    }

    private void printGeneration() {
        System.out.println("[Game of Life] Generation: " + getGeneration() + "/" + getMaxGeneration());
    }

    private void printEmptyLine() {
        System.out.println();
    }

    private String getCellDisplay(int state) {
        if (state == 1) return gameConfig.getConsoleCellAliveDisplay();
        else return gameConfig.getConsoleCellDeadDisplay();
    }

    private void printIndividualCell(int state) {
        System.out.print(getCellDisplay(state));
    }

    private void loopGameOfLife() {
        if (gameConfig.isShowGenerationInConsole()) printCellsToConsole();
        while (generation < getMaxGeneration()) {
            int[][] newEnvironment = generateNextEnvironment();
            addHistory(getCells());
            copyNewGenerationToCurrent(newEnvironment);
            incrementGeneration();
            if (gameConfig.isShowGenerationInConsole()) {
                printCellsToConsole();
                triggerDelay();
            }
        }
        System.out.println("[Game of Life] Generation complete...");
    }

    private void triggerDelay() {
        try {
            Thread.sleep(gameConfig.getUpdateDelay());
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

        int randomDeathChance = getNumberBetween(0, gameConfig.getRandomDeathChance());

        if (cell == 1 && lifeCounter < 2) return 0;
        else if (cell == 1 && lifeCounter < 4) {
            if (randomDeathChance == 1 && gameConfig.isUseRandomDeathChance()) return 0;
            else return 1;
        }
        else if (cell == 1 && lifeCounter > 3) return 0;
        else if (cell == 0 && lifeCounter == 3) {
            if (randomDeathChance == 1 && gameConfig.isUseRandomDeathChance()) return 0;
            else return 1;
        }
        return 0;
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

    public int getMaxGeneration() {
        return gameConfig.getMaxGeneration();
    }
}
