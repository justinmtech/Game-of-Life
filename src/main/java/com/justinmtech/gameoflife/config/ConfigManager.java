package com.justinmtech.gameoflife.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.justinmtech.gameoflife.generation.GenerationType;
import java.io.File;
import java.io.IOException;

public class ConfigManager {
    private GameConfig gameConfig;

    public ConfigManager() {
        gameConfig = new GameConfig();
        if (saveDefaultConfigTest()) {
            System.out.println("[" + gameConfig.getGameTitle() + "] Default config file created. You may modify the config.yaml to customize the game's settings.");
        }
        boolean configLoaded = loadConfig();
        if (configLoaded) System.out.println("[" + gameConfig.getGameTitle() + "] Config loaded successfully!");

    }

    private boolean saveDefaultConfigTest() {
        File file = new File("config.yaml");
        if (!file.exists()) {
            GameConfig config = new GameConfig();
            config.setGenerator(GenerationType.DYNAMIC);
            config.setUpdateDelay(50);
            config.setGenerationChance(5);
            config.setHeight(500);
            config.setWidth(500);
            config.setMaxGeneration(1000);
            config.setSeed(new int[]{0, 0, 0, 1});
            config.setUseSeed(false);
            config.setBackgroundColor("BLACK");
            config.setCellColor("GREEN");
            config.setGameTitle("Game of Life");
            config.setRandomDeathChance(100);
            config.setUseRandomDeathChance(false);
            config.setUseRandomCellColors(false);
            config.setDisableGUI(false);
            config.setShowGenerationInConsole(false);
            config.setConsoleCellDeadDisplay(" ");
            config.setConsoleCellAliveDisplay("*");
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            try {
                objectMapper.writeValue(new File("config.yaml"), config);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("[" + gameConfig.getGameTitle() + "] Could not save default config to path.");
            }
        }
        return false;
    }

    private boolean loadConfig() {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        File file = new File("config.yaml");
        if (file.exists()) {
            try {
                gameConfig = objectMapper.readValue(file, GameConfig.class);
                System.out.println("[" + gameConfig.getGameTitle() + "] Configuration:\n" + gameConfig.toString());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("[" + gameConfig.getGameTitle() + "] Could not load config.");
            }
        } else {
            System.out.println("[" + gameConfig.getGameTitle() + "] Config file does not exist..");
        }
        return false;
    }

    public GameConfig getGameConfig() {
        return gameConfig;
    }
}
