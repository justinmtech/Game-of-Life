package com.justinmtech.gameoflife;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GUI extends Canvas {
    private Environment environment;

    public GUI(Environment environment) {
        this.environment = environment;
        main();
    }

    public GUI() {
        main();
    }

    public void main() {
        JFrame frame = new JFrame("Cellular Automata");
        Canvas canvas = new GUI();
        canvas.setSize(500, 500);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }

    public void paint(Graphics g) {
        //int y = 0;
        //int generation = 0;
       //while (generation < environment.getHistory().size() - 1) {
            //for (int x = 0; x < environment.getWidth(); x++) {
            //    int cell =  getCellInGeneration(generation, x, y);
                //fillSquare(g, y, cell);
                //g.fillRect(x, y, 1, 1);
           // }
            //y++;
        //}
        g.fillRect(0, 0, 1, 1);
    }

    private void fillSquare(Graphics g, int i, int[] cells) {
        for (int j = 0; j < cells.length; j++) {
            if (cells[j] == 1) {
                g.fillRect(j, i, 1, 1);
            }
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
