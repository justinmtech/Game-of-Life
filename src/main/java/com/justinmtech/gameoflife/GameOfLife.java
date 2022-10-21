package com.justinmtech.gameoflife;

public class GameOfLife {

    public static void main(String[] args) {
        ConfigManager configManager = new ConfigManager();
        GameConfig gameConfig = configManager.getGameConfig();
        Environment environment = new Environment(configManager);
        environment.run(gameConfig.isUseSeed());
        GUI gui = new GUI(environment, gameConfig);
        gui.run();
    }
}


