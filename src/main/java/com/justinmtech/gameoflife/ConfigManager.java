package com.justinmtech.gameoflife;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.*;

public class ConfigManager {
    private GameConfig gameConfig;

    public ConfigManager() {
        gameConfig = new GameConfig();
        if (saveDefaultConfigTest()) {
            System.out.println("[Game of Life] Default config file created. You may modify the config.yaml to customize the game's settings.");
        }
        boolean configLoaded = loadConfig();
        if (configLoaded) {
            System.out.println("[Game of Life] Config loaded successfully!");
        } else {
            setDefaultConfig();
        }
    }

    private boolean saveDefaultConfigTest() {
        File file = new File("config.yaml");
        if (!file.exists()) {
            GameConfig config = new GameConfig();
            config.setUpdateDelay(50);
            config.setGenerationChance(5);
            config.setHeight(500);
            config.setWidth(500);
            config.setMaxGeneration(1000);
            config.setSeed(new int[]{0, 0, 0, 1});
            config.setUseSeed(false);
            config.setBackgroundColor("WHITE");
            config.setCellColor("BLACK");
            config.setGameTitle("Game of Life");
            config.setRandomDeathChance(100);
            config.setUseRandomDeathChance(false);
            config.setUseRandomCellColors(false);
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            try {
                objectMapper.writeValue(new File("config.yaml"), config);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("[Game of Life] Could not save default config to path.");
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
                System.out.println("[Game of Life] Configuration info:\n" + gameConfig.toString());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("[Game of Life] Could not load config.");
            }
        } else {
            System.out.println("[Game of Life] Config file does not exist..");
        }
        return false;
    }

    private void setDefaultConfig() {
        gameConfig.setSeed(new int[]{0,1,1,1,0,0,1,0,0,0,0,0,1});
        gameConfig.setUpdateDelay(50);
        gameConfig.setWidth(500);
        gameConfig.setHeight(500);
        gameConfig.setGenerationChance(10);
        System.out.println("[Game of Life] Default configuration set.");
    }

    public GameConfig getGameConfig() {
        return gameConfig;
    }
}
