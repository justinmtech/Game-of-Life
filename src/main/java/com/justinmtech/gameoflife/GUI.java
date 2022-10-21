package com.justinmtech.gameoflife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class GUI extends Canvas implements KeyListener {
    private final GameConfig gameConfig;
    private final Environment environment;
    private int delay;
    private boolean running;

    public GUI(Environment environment, GameConfig gameConfig) {
        this.running = true;
        this.gameConfig = gameConfig;
        this.environment = environment;
        this.delay = gameConfig.getUpdateDelay();
    }

    public void run() {
        System.out.println("[Game of Life] GUI opening...");
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
        while (generation < environment.getMaxGeneration() - 1 && running) {
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
        System.out.println("[Game of Life] Simulation complete!");
        System.exit(0);
    }

    private void fillSquare(Graphics g, int x, int y, int cell) {
        if (cell == 1) {
            g.setColor(getColorFromString(gameConfig.getCellColor()));
            g.fillRect(x, y, 1, 1);
        } else {
            g.setColor(getColorFromString(gameConfig.getBackgroundColor()));
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

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("typed");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_W) {
            delay++;
            System.out.println("Delay raised to: " + delay);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            if (delay > 0) {
                delay--;
                System.out.println("Delay lowered to: " + delay);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
