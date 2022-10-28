package com.justinmtech.gameoflife;

import com.justinmtech.cellularautomata.CellularAutomata;
import com.justinmtech.cellularautomata.Drawing;
import com.justinmtech.gameoflife.config.ConfigManager;
import com.justinmtech.gameoflife.config.GameConfig;
import com.justinmtech.gameoflife.display.GUI;
import com.justinmtech.gameoflife.generation.Environment;
import com.justinmtech.gameoflife.generation.GenerationType;

public class GameOfLife {

    public static void main(String[] args) {
        ConfigManager configManager = new ConfigManager();
        GameConfig gameConfig = configManager.getGameConfig();
        GenerationType generator = gameConfig.getGenerator();
        if (generator == GenerationType.DYNAMIC) {
            Environment environment = new Environment(configManager);
            environment.run(gameConfig.isUseSeed());
            GUI gui = new GUI(environment, gameConfig);
            gui.run();
        } else if (generator == GenerationType.STATIC) {
            CellularAutomata ca = new CellularAutomata();
            ca.setHeight(gameConfig.getHeight());
            ca.setWidth(gameConfig.getWidth());
            ca.setSeed(gameConfig.getSeed());
            ca.run();
            Drawing draw = new Drawing(ca);
            draw.run(gameConfig.getWidth(), gameConfig.getHeight());
        }
    }
}


