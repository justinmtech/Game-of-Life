package com.justinmtech.gameoflife;

public class Environment {
    private boolean running = true;
    private final int size;
    private final int width;
    private final int[][] environment;
    private int generation;
    private final int[] rules = {0,1,0,0,1,0,1,1};

    public Environment(int size, int width) {
        this.size = size;
        this.width = width;
        environment = new int[width][size];
        this.generation = 1;
        int y = 0;
        while (y < size) {
            for (int x = 0; x < width; x++) {
                int r = getRandomNumber(0, 10);
                if (r == 9) {
                    environment[x][y] = 1;
                } else {
                    environment[x][y] = 0;
                }
            }
            y++;
        }
        System.out.println("Generation: " + 0);
        displayEnvironment();
        generateEnvironment();
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private int getValueAtLocation(int x, int y) {
        return environment[x][y];
    }

    private int updateValueAtLocation(int x, int y, int value) {
        return environment[x][y] = value;
    }

    public void displayEnvironment() {
        int y = 0;
        while (y < size) {
            for (int x = 0; x < environment.length; x++) {
                int state = environment[x][y];
                System.out.print(getCellDisplay(state));
            }
            System.out.println();
            y++;
        }
    }

    private int[] getCellsOnLine(int y) {
        int[] cells = new int[width];
        for (int i = 0; i < width; i++) {
            cells[i] = environment[i][y];
        }
        return cells;
    }

    private void generateLine(int y) {
        int[] newGen = new int[width];
        int prev = y - 1;
        for (int x = 1; x < getCellsOnLine(y).length - 1; x++) {
            int up = 0;
            if (prev > 0) up = getCellsOnLine(prev - 1)[x];
            int down = 0;
            if (prev < size) down = getCellsOnLine(prev + 1)[x];
            int left = getCellsOnLine(prev)[x - 1];
            int me = getCellsOnLine(prev)[x];
            int right = getCellsOnLine(prev)[x + 1];
            int topRight = 0;
            if (prev - 1 >= 0) topRight = getCellsOnLine(prev - 1)[x + 1];
            int topLeft = 0;
            if (prev - 1 >= 0) topLeft = getCellsOnLine(prev - 1)[x - 1];
            int bottomRight = 0;
            if (prev + 1 < size) bottomRight = getCellsOnLine(prev + 1)[x + 1];
            int bottomLeft = 0;
            if (prev + 1 < size) bottomLeft = getCellsOnLine(prev + 1)[x - 1];
            int neighbors[] = {topLeft, up, topRight, left, me, right, bottomLeft, down, bottomRight};

            newGen[x] = lifeOrDeath(me, neighbors);
            if (y < size) {
            environment[x][y] = newGen[x];
            }
        }
    }

    private void generateEnvironment() {
        while (running) {
            for (int y = 1; y < size - 1; y++) {
                generateLine(y);
            }
            System.out.println("Generation: " + generation);
            displayEnvironment();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            generation++;
        }
    }

    private int lifeOrDeath(int cell, int[] neighbors) {
        int lifeCounter = 0;
        for (int i : neighbors) if (i == 1) lifeCounter++;

        if (cell == 1 && lifeCounter < 2) return 0;
        if (cell == 1 && lifeCounter <= 3) return 1;
        if (cell == 1 && lifeCounter > 3) return 0;
        if (cell == 0 && lifeCounter < 1) return 1;
        return cell;
    }

    private String getCellDisplay(int state) {
        return state == 1 ? "⬛" : "☐";
    }
}
