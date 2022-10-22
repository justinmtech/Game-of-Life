package com.justinmtech.gameoflife.display;

import com.justinmtech.gameoflife.generation.Cell;
import com.justinmtech.gameoflife.generation.Coordinates;
import com.justinmtech.gameoflife.generation.Environment;
import com.justinmtech.gameoflife.config.GameConfig;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

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
        JFrame frame = new JFrame(gameConfig.getGameTitle());
        Canvas canvas = new GUI(environment, gameConfig);
        canvas.setSize(environment.getWidth(), environment.getHeight());
        canvas.setBackground(getColorFromString(gameConfig.getBackgroundColor()));
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
                    Cell cell = getCellInGeneration(generation, x, y);
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

    private void fillSquare(Graphics g, int x, int y, Cell cell) {
        if (cell.getState() == 1) {
            if (gameConfig.isUseRandomCellColors()) {
                g.setColor(pickRandomColor());
            } else {
                g.setColor(cell.getColor());
            }
            g.fillRect(x, y, 1, 1);
        } else {
            g.setColor(getColorFromString(gameConfig.getBackgroundColor()));
            g.fillRect(x, y, 1, 1);
        }
    }

    private List<Map<Coordinates, Cell>> getHistory() {
        return environment.getHistory();
    }

    private Map<Coordinates, Cell> getGeneration(int generation) {
        return getHistory().get(generation);
    }

    private Cell getCellInGeneration(int generation, int x, int y) {
        return getGeneration(generation).get(new Coordinates(x, y));
    }

    private Color getColorFromString(String color) {
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

    private Color pickRandomColor() {
        int number = Environment.getNumberBetween(0, 9);
        switch (number) {
            case 1: return Color.BLACK;
            case 2: return Color.RED;
            case 3: return Color.BLUE;
            case 4: return Color.GREEN;
            case 5: return Color.YELLOW;
            case 6: return Color.MAGENTA;
            case 7: return Color.PINK;
            case 8: return Color.CYAN;
            case 9: return Color.ORANGE;
            default: return Color.WHITE;
        }
    }
}
