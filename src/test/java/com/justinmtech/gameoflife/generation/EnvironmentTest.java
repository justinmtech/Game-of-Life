package com.justinmtech.gameoflife.generation;

import com.justinmtech.gameoflife.config.ConfigManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class EnvironmentTest {
    private static Environment environment;
    private static ConfigManager configManager;

    @BeforeAll
    static void setup() {
        configManager = new ConfigManager();
        environment = new Environment(configManager);
    }

    @Test
    void getHeight() {
        int height = environment.getHeight();
        Assertions.assertEquals(environment.getHeight(), height);
    }

    @Test
    void getWidth() {
        int width = environment.getWidth();
        Assertions.assertEquals(environment.getWidth(), width);
    }

    @Test
    void getHistory() {
        int maxGeneration = environment.getMaxGeneration();
        Assertions.assertEquals(configManager.getGameConfig().getMaxGeneration(), maxGeneration);
    }

    @Test
    void getMaxGeneration() {
        int maxGen = configManager.getGameConfig().getMaxGeneration();
        Assertions.assertEquals(environment.getMaxGeneration(), maxGen);
    }

    @Test
    void getNumberBetween() {
        Assertions.assertTrue(Environment.getNumberBetween(0, 1) >= 0);
        Assertions.assertTrue(Environment.getNumberBetween(0, 1) <= 1);
    }

    @Test
    void environmentRunsSuccessfully() {
        Assertions.assertTrue(environment.run(true));
        Assertions.assertTrue(environment.run(false));
    }

}