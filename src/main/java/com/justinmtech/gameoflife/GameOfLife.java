package com.justinmtech.gameoflife;

import com.justinmtech.gameoflife.cellularautomata.CellularAutomata;
import com.justinmtech.gameoflife.cellularautomata.StaticDisplay;
import com.justinmtech.gameoflife.config.ConfigManager;
import com.justinmtech.gameoflife.config.GameConfig;
import com.justinmtech.gameoflife.display.DynamicDisplay;
import com.justinmtech.gameoflife.generation.Environment;
import com.justinmtech.gameoflife.generation.GenerationType;

import javax.naming.ConfigurationException;

public class GameOfLife {

    public static void main(String[] args) throws ConfigurationException, InterruptedException {
        ConfigManager configManager = new ConfigManager();
        GameConfig gameConfig = configManager.getGameConfig();
        GenerationType generator = gameConfig.getGenerator();
        if (generator == GenerationType.DYNAMIC) {
            Environment environment = new Environment(configManager);
            Thread environmentThread = new Thread(environment);
            environmentThread.start();
            DynamicDisplay gui = new DynamicDisplay(environment, gameConfig);
            Thread guiThread = new Thread(gui);
            if (gameConfig.isPlayInReverse()) {
                System.out.println("[" + gameConfig.getGameTitle() + "] Playing in reverse.. Please wait while the environment generates.");
                environmentThread.join();
                guiThread.start();
            } else {
                guiThread.start();
            }
        } else if (generator == GenerationType.STATIC) {
            if (gameConfig.getSeed().length != 8) {
                throw new ConfigurationException("The seed must be 8 integers long for static generations.");
            }
            CellularAutomata ca = new CellularAutomata(gameConfig);
            ca.run();
            StaticDisplay draw = new StaticDisplay(ca, gameConfig);
            draw.run();
        }
    }
}


