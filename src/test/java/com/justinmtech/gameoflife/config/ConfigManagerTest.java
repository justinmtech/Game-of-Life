package com.justinmtech.gameoflife.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ConfigManagerTest {
    private static ConfigManager configManager;

    @BeforeAll
    public static void setup() {
        configManager = new ConfigManager();
    }

    @Test
    void getGameConfig() {
        Assertions.assertNotNull(configManager);
    }

}