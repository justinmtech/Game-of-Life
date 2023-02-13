package com.justinmtech.gameoflife.cellularautomata;

import com.justinmtech.gameoflife.config.GameConfig;
import com.justinmtech.gameoflife.display.DynamicDisplay;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StaticDisplay extends Canvas {
    private final CellularAutomata cellularAutomata;
    private final GameConfig config;

    public StaticDisplay(CellularAutomata cellularAutomata, GameConfig config) {
        this.cellularAutomata = cellularAutomata;
        this.config = config;
    }

    public void run() {
        JFrame frame = new JFrame(config.getGameTitle());
        DynamicDisplay.setIcons(frame);
        Canvas canvas = new StaticDisplay(cellularAutomata, config);
        canvas.setSize(config.getWidth(), config.getHeight());
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
                if (!config.isUseRandomCellColors()) {
                    g.setColor(DynamicDisplay.getGUIColorFromString(config.getCellColor()));
                } else {
                    g.setColor(DynamicDisplay.getRandomGUIColor());
                }
                g.fillRect(j, i, 1, 1);
            } else {
                if (!config.isUseRandomCellColors()) {
                    g.setColor(DynamicDisplay.getGUIColorFromString(config.getBackgroundColor()));
                } else {
                    g.setColor(DynamicDisplay.getRandomGUIColor());
                }
                g.fillRect(j, i, 1, 1);
            }
        }
    }

}
