package com.justinmtech.gameoflife.display;

import com.justinmtech.gameoflife.config.GameConfig;
import com.justinmtech.gameoflife.generation.Environment;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DynamicDisplay extends Canvas implements Runnable {
    private final GameConfig gameConfig;
    private final Environment environment;
    private final int delay;

    public DynamicDisplay(Environment environment, GameConfig gameConfig) {
        this.gameConfig = gameConfig;
        this.environment = environment;
        this.delay = gameConfig.getUpdateDelay();
    }

    public void run() {
        guiOpening();
        Canvas canvas = new DynamicDisplay(environment, gameConfig);
        canvas.setSize(environment.getWidth(), environment.getHeight());
        canvas.setBackground(getGUIColorFromString(gameConfig.getBackgroundColor()));
        JFrame frame = new JFrame(gameConfig.getGameTitle());
        setIcons(frame);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }

    public static void setIcons(JFrame frame) {
        frame.setIconImages(List.of(getIcon("icon-small.png"), getIcon("icon-large.png")));
    }

    private static Image getIcon(String file) {
        return Toolkit.getDefaultToolkit().getImage(file);
    }

    public void paint(Graphics g) {
        if (!gameConfig.isPlayInReverse()) {
            playNormal(g);
        } else {
            playInReverse(g);
        }
    }

    private void guiOpening() {
        System.out.println("[" + gameConfig.getGameTitle() + "] GUI opening...");
    }

    private void playNormal(Graphics g) {
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
            triggerDelay();
            generation++;
        }
        finish();
    }

    private void playInReverse(Graphics g) {
        int generation = environment.getMaxGeneration() - 2;
        while (generation > 0) {
            int y = environment.getHeight() - 1;
            while (y > 0) {
                for (int x = environment.getWidth() - 1; x > 0; x--) {
                    int cell = getCellInGeneration(generation, x, y);
                    fillSquare(g, x, y, cell);
                }
                y--;
            }
            triggerDelay();
            generation--;
        }
        finish();
    }

    private void triggerDelay() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void finish() {
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

    public static Color getGUIColorFromString(String color) {
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

    public static Color getRandomGUIColor() {
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
