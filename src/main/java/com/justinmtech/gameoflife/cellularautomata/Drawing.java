package com.justinmtech.gameoflife.cellularautomata;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Drawing extends Canvas {
    private final CellularAutomata cellularAutomata;

    public Drawing(CellularAutomata cellularAutomata) {
        this.cellularAutomata = cellularAutomata;
    }

    public void run(int width, int height) {
        JFrame frame = new JFrame("Cellular Automata");
        Canvas canvas = new Drawing(cellularAutomata);
        canvas.setSize(width, height);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }

    public void paint(Graphics g) {
        List<int[]> cellHistory = cellularAutomata.getCellHistory();
        for (int i = 0; i < cellHistory.size(); i++) {
            int[] cells = cellHistory.get(i);
            fillSquare(g, i, cells);
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
