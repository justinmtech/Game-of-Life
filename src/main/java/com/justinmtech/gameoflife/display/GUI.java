package com.justinmtech.gameoflife.display;

import com.justinmtech.gameoflife.config.GameConfig;
import com.justinmtech.gameoflife.generation.Environment;

import javax.swing.*;
import java.awt.*;

public class GUI extends Canvas {
    private final GameConfig gameConfig;
    private final Environment environment;
    private final int delay;

    public GUI(Environment environment, GameConfig gameConfig) {
        this.gameConfig = gameConfig;
        this.environment = environment;
        this.delay = gameConfig.getUpdateDelay();
    }

    public void run() {
        System.out.println("[" + gameConfig.getGameTitle() + "] GUI opening...");
        Canvas canvas = new GUI(environment, gameConfig);
        canvas.setSize(environment.getWidth(), environment.getHeight());
        canvas.setBackground(getGUIColorFromString(gameConfig.getBackgroundColor()));
        JFrame frame = new JFrame(gameConfig.getGameTitle());
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }

    @SuppressWarnings("BusyWait")
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
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            generation++;
        }
        System.out.println("[" + gameConfig.getGameTitle() + "] Simulation complete!");
        System.exit(0);
    }

    private void fillSquare(Graphics g, int x, int y, int cell) {
        if (cell < 0 || cell > 1) return;
        switch (cell) {
            case 1:
                if (gameConfig.isUseRandomCellColors()) g.setColor(getRandomGUIColor());
                else g.setColor(getGUIColorFromString(gameConfig.getCellColor()));
                break;
            case 0:
                g.setColor(getGUIColorFromString(gameConfig.getBackgroundColor()));
        }
        g.fillRect(x, y, 1, 1);
    }

    private int getCellInGeneration(int generation, int x, int y) {
        return environment.getHistory().get(generation)[x][y];
    }

    private Color getGUIColorFromString(String color) {
        return switch (color.toUpperCase()) {
            case "BLACK" -> Color.BLACK;
            case "RED" -> Color.RED;
            case "GREEN" -> Color.GREEN;
            case "YELLOW" -> Color.YELLOW;
            case "ORANGE" -> Color.ORANGE;
            case "BLUE" -> Color.BLUE;
            case "CYAN" -> Color.CYAN;
            case "PINK" -> Color.PINK;
            case "MAGENTA", "PURPLE" -> Color.MAGENTA;
            case "GRAY" -> Color.GRAY;
            case "DARK_GRAY" -> Color.DARK_GRAY;
            case "LIGHT_GRAY" -> Color.LIGHT_GRAY;
            default -> Color.WHITE;
        };
    }

    private Color getRandomGUIColor() {
        int number = Environment.getNumberBetween(0, 9);
        return switch (number) {
            case 1 -> Color.BLACK;
            case 2 -> Color.RED;
            case 3 -> Color.BLUE;
            case 4 -> Color.GREEN;
            case 5 -> Color.YELLOW;
            case 6 -> Color.MAGENTA;
            case 7 -> Color.PINK;
            case 8 -> Color.CYAN;
            case 9 -> Color.ORANGE;
            default -> Color.WHITE;
        };
    }
}
