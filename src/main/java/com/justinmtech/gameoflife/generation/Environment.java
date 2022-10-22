package com.justinmtech.gameoflife.generation;

import com.justinmtech.gameoflife.config.ConfigManager;
import com.justinmtech.gameoflife.config.GameConfig;

import java.awt.*;
import java.util.*;
import java.util.List;

//TODO Create new data type for cells so they can have a state other than 1 or 0. This will make them more modular.
public class Environment {
    private final GameConfig gameConfig;
    private int generation;
    private Map<int[], Cell> cells;
    private final List<Map<int[], Cell>> history;

    public Environment(ConfigManager configManager) {
        this.gameConfig = configManager.getGameConfig();
        this.history = new ArrayList<>();
        this.generation = 1;
        //this.cells = new int[gameConfig.getWidth()][gameConfig.getHeight()];
        this.cells = new HashMap<>();
    }

    public int getHeight() {
        return gameConfig.getHeight();
    }

    public int getWidth() {
        return gameConfig.getWidth();
    }

    public List<Map<int[], Cell>> getHistory() {
        return history;
    }

    private int[] getSeed() {
        return gameConfig.getSeed();
    }

    public void run(boolean seed) {
        if (seed && getSeed() != null) {
            System.out.println("[" + gameConfig.getGameTitle() + "] Now generating game with seed.. [" + Arrays.toString(getSeed()) + "]");
            generateCellsFromSeed();
        } else {
            System.out.println("[" + gameConfig.getGameTitle() + "] Now generating game with chance.." + " [cell chance: " + gameConfig.getGenerationChance() + "]");
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

    private Cell getCell(int x, int y) {
        if ((x < 0 || x > getWidth() - 1) || (y < 0 || y > getHeight() - 1)) return new Cell(0, x, y);
        return cells.get(new int[]{x, y});
    }

    private List<Cell> generateLineOfCellsAtY(int y) {
        List<Cell> newLine = new ArrayList<>();
        int prev = y - 1;
        for (int x = 1; x < getWidth(); x++) {
            Cell cell = getLifeOrDeath(getCell(x, prev), getNeighborCells(prev, x));
            cell.setCoordinates(x, y);
            newLine.add(cell);
        }
        return newLine;
    }

    private List<Cell> getNeighborCells(int y, int x) {
        Cell topLeft = getCell(x - 1, y - 1);
        Cell up = getCell(x, y - 1);
        Cell topRight = getCell(x + 1, y - 1);
        Cell left = getCell(x - 1, y);
        Cell right = getCell(x + 1, y);
        Cell bottomLeft = getCell(x - 1, y + 1);
        Cell down = getCell(x, y + 1);
        Cell bottomRight = getCell(x + 1, y + 1);
        return Arrays.asList(topLeft, up, topRight, left, right, bottomLeft, down, bottomRight);
    }


    private Map<Coordinates, Cell> generateNextEnvironment() {
        Map<Coordinates, Cell> newEnvironment = new HashMap<>();
        for (int y = 1; y < getHeight() - 1; y++) {
            List<Cell> line = generateLineOfCellsAtY(y);
            for (int x = 0; x < getWidth(); x++) {
                newEnvironment.get(new Coordinates(x, y - 1)).setState(line.get(x).getState());
            }
        }
        return newEnvironment;
    }

    private void printCellsToConsole() {
        int y = 0;
        printGeneration();
        while (y < getHeight() - 1) {
            for (int x = 0; x < getWidth(); x++) {
                printIndividualCell(getCell(x, y).getState());
            }
            printEmptyLine();
            y++;
        }
    }

    private void printGeneration() {
        System.out.println("[" + gameConfig.getGameTitle() + "] Generation: " + getGeneration() + "/" + getMaxGeneration());
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
            Map<Coordinates, Cell> newEnvironment = generateNextEnvironment();
            addHistory(getCells());
            copyNewGenerationToCurrent(newEnvironment);
            incrementGeneration();
            if (gameConfig.isShowGenerationInConsole()) {
                printCellsToConsole();
                triggerDelay();
            }
        }
        System.out.println("[" + gameConfig.getGameTitle() + "] Generation complete...");
    }

    private void triggerDelay() {
        try {
            Thread.sleep(gameConfig.getUpdateDelay());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void copyNewGenerationToCurrent(Map<int[], Cell> newGeneration) {
        setCells(newGeneration);
    }

    public void setCells(Map<int[], Cell> cells) {
        this.cells = cells;
    }

    private int getLiveCellsFromNeighbors(List<Cell> neighbors) {
        int lifeCounter = 0; //1 = life, 0 = death
        for (Cell c : neighbors) if (c.getState() == 1) lifeCounter++;
        return lifeCounter;
    }

    private Color generateColorBasedOnNeighbors(List<Cell> neighbors) {
        List<Color> colors = getColorsOfNeighbors(neighbors);
        return getPrimaryColor(colors);
    }

    private List<Color> getColorsOfNeighbors(List<Cell> neighbors) {
        List<Color> colors = new ArrayList<>();
        for (Cell c : neighbors) {
            colors.add(c.getColor());
        }
        return colors;
    }

    private Color getPrimaryColor(List<Color> colors) {
        int green = 0;
        int blue = 0;
        for (Color color : colors) {
            switch (color.toString().toUpperCase()) {
                case "BLUE": blue++;
                case "GREEN": green++;
            }
        }
        if (green > blue) return Color.GREEN;
        if (blue > green) return Color.BLUE;
        return Color.BLUE;
    }

    private Cell getLifeOrDeath(Cell cell, List<Cell> neighbors) {
        int lifeCounter = getLiveCellsFromNeighbors(neighbors);
        int randomDeathChance = getNumberBetween(0, gameConfig.getRandomDeathChance());
        int cellState = cell.getState();

        if (cellState == 1 && lifeCounter < 2) {
            cell.setState(0);
            return cell;
        }
        else if (cellState == 1 && lifeCounter < 4) {
            if (randomDeathChance == 1 && gameConfig.isUseRandomDeathChance()) {
                cell.setColor(Color.BLUE);
                cell.setState(0);
            }
            else {
                cell.setState(1);
                cell.setColor(Color.RED);
            }
            return cell;
        }
        else if (cellState == 1 && lifeCounter > 3) {
            cell.setState(0);
            return cell;
        } else if (cellState == 0 && lifeCounter == 3) {
            if (randomDeathChance == 1 && gameConfig.isUseRandomDeathChance()) {
                cell.setState(0);
            }
            else {
                cell.setState(1);
            }
            return cell;
        }
        cell.setState(0);
        return cell;
    }

    private int getGeneration() {
        return generation;
    }

    private void incrementGeneration() {
        this.generation++;
    }

    private void setCellState(int x, int y, int state) {
        getCell(x, y).setState(state);
    }

    private void addHistory(Map<int[], Cell> environment) {
        this.history.add(environment);
    }

    private Map<int[], Cell> getCells() {
        return cells;
    }

    public int getMaxGeneration() {
        return gameConfig.getMaxGeneration();
    }
}
