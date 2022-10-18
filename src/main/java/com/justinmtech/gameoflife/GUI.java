package com.justinmtech.gameoflife;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GUI extends Canvas {
    private final Environment environment;
    private int frameRate;

    public GUI(Environment environment) {
        this.environment = environment;
        this.frameRate = 25;
    }

    private int convertFrameRateToMilliseconds() {
        return (int) (getFrameRate() / 3.2808398950131);
    }

    public void run() {
        JFrame frame = new JFrame("Conway's Game of Life");
        Canvas canvas = new GUI(environment);
        canvas.setSize(environment.getWidth(), environment.getHeight());
        canvas.setBackground(Color.BLACK);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }

    public void paint(Graphics g) {
        int generation = 0;
        while (generation < environment.getMaxGeneration() - 1) {
            int y = 0;
            while (y < environment.getHeight() - 1) {
                for (int x = 0; x < environment.getWidth() - 1; x++) {
                    int cell = getCellInGeneration(generation, x, y);
                    fillSquare(g, x, y, cell);
                }
                y++;
            }
            try {
                Thread.sleep(convertFrameRateToMilliseconds());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            generation++;
        }
    }

    private void fillSquare(Graphics g, int x, int y, int cell) {
        if (cell == 1) {
            g.setColor(Color.GREEN);
            g.fillRect(x, y, 1, 1);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(x, y, 1, 1);
        }
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

    public int getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(int frameRate) {
        this.frameRate = frameRate;
    }
}
