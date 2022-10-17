package com.justinmtech.gameoflife;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GUI extends Canvas {
    private List<int[][]> history;
    private int generations;
    private int width;
    private int size;

    public GUI(List<int[][]> history, int generations, int width, int size) {
        this.history = history;
    }

    public void main() {
        JFrame frame = new JFrame("Cellular Automata");
        Canvas canvas = new GUI(history, generations, width, size);
        canvas.setSize(1920, 1800);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }

    public void paint(Graphics g) {
        int y = 0;
        int generation = 0;
        while (generation < generations) {
            for (int x = 0; x < size; x++) {
                int cell = history.get(0)[x][y];
                //fillSquare(g, y, cell);
                g.fillRect(x, y, 1, 1);
/*                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
            y++;
        }
    }

    private void fillSquare(Graphics g, int i, int[] cells) {
        for (int j = 0; j < cells.length; j++) {
            if (cells[j] == 1) {
                g.fillRect(j, i, 1, 1);
            }
        }
    }
}
