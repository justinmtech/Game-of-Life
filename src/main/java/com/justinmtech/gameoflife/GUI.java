package com.justinmtech.gameoflife;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GUI extends Canvas {
    private Environment environment;

    public GUI(Environment environment) {
        this.environment = environment;
    }


    public void main() {
        JFrame frame = new JFrame("Cellular Automata");
        Canvas canvas = new GUI(environment);
        canvas.setSize(1920, 1080);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }

    public void paint(Graphics g) {
        int generation = 0;
        while (generation < 1000) {
            int y = 0;
            while (y < environment.getHeight()) {
                for (int x = 0; x < environment.getWidth() - 1; x++) {
                    int cell = getCellInGeneration(generation, x, y);
                    fillSquare(g, x, y, cell);
                }
                y++;
            }
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            generation++;
        }
    }

    private void fillSquare(Graphics g, int x, int y, int cell) {
        if (cell == 1) {
            g.clearRect(x, y, 1, 1);
        } else {
            g.fillRect(x, y, 1, 1);
        }
    }

    private void resetFrame(Graphics g) {
        int width = 1920;
        int height = 1080;
        int currentHeight = 0;
        while (currentHeight < height) {
            for (int x = 0; x < width; x++) {
                g.clearRect(x, currentHeight, 1, 1);
            }
            currentHeight++;
        }

    }

    public Environment getEnvironment() {
        return environment;
    }

    private List<int[][]> getHistory() {
        return environment.getHistory();
    }

    private int[][] getGeneration(int generation) {
        return getHistory().get(generation);
    }

    private int getCellInGeneration(int generation, int x, int y) {
        return getGeneration(generation)[x][y];
    }
}
