package com.justinmtech.gameoflife.config;

import com.justinmtech.gameoflife.generation.GenerationType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class GameConfigTest {
    private static GameConfig config;

    @BeforeAll
    public static void setup() {
        config = new GameConfig();
    }

    @Test
    void getGenerator() {
        if (config.getGenerator() == GenerationType.DYNAMIC) {
            Assertions.assertEquals(GenerationType.DYNAMIC, config.getGenerator());
        } else if (config.getGenerator() == GenerationType.STATIC){
            Assertions.assertEquals(GenerationType.STATIC, config.getGenerator());
        }
    }

    @Test
    void setGenerator() {
        config.setGenerator(GenerationType.STATIC);
        Assertions.assertEquals(GenerationType.STATIC, config.getGenerator());
        config.setGenerator(GenerationType.DYNAMIC);
        Assertions.assertEquals(GenerationType.DYNAMIC, config.getGenerator());
    }

    @Test
    void getUpdateDelay() {
        int updateDelay = config.getUpdateDelay();
        Assertions.assertEquals(updateDelay, config.getUpdateDelay());
    }

    @Test
    void setUpdateDelay() {
        int newDelay = 150;
        config.setUpdateDelay(newDelay);
        Assertions.assertEquals(newDelay, config.getUpdateDelay());
    }

    @Test
    void getGenerationChance() {
        int chance = config.getGenerationChance();
        Assertions.assertEquals(chance, config.getGenerationChance());
    }

    @Test
    void setGenerationChance() {
        int newChance = 100;
        config.setGenerationChance(newChance);
        Assertions.assertEquals(newChance, config.getGenerationChance());
    }

    @Test
    void getHeight() {
        int height = config.getHeight();
        Assertions.assertEquals(height, config.getHeight());
    }

    @Test
    void setHeight() {
        int newHeight = 1000;
        config.setHeight(newHeight);
        Assertions.assertEquals(newHeight, config.getHeight());
    }

    @Test
    void getWidth() {
        int width = config.getWidth();
        Assertions.assertEquals(width, config.getWidth());
    }

    @Test
    void setWidth() {
        int newWidth = 1500;
        config.setWidth(newWidth);
        Assertions.assertEquals(newWidth, config.getWidth());
    }

    @Test
    void getSeed() {
        int[] seed = config.getSeed();
        Assertions.assertEquals(seed, config.getSeed());
    }

    @Test
    void setSeed() {
        int[] newSeed = {0,1,0,1,0,1,0,1};
        config.setSeed(newSeed);
        Assertions.assertEquals(newSeed, config.getSeed());
    }

    @Test
    void getMaxGeneration() {
        int maxGen = config.getMaxGeneration();
        Assertions.assertEquals(maxGen, config.getMaxGeneration());
    }

    @Test
    void setMaxGeneration() {
        int newMaxGen = 10000;
        config.setMaxGeneration(newMaxGen);
        Assertions.assertEquals(newMaxGen, config.getMaxGeneration());
    }

    @Test
    void isUseSeed() {
        boolean usingSeed = config.isUseSeed();
        Assertions.assertEquals(usingSeed, config.isUseSeed());
    }

    @Test
    void setUseSeed() {
        config.setUseSeed(true);
        Assertions.assertTrue(config.isUseSeed());
        config.setUseSeed(false);
        Assertions.assertFalse(config.isUseSeed());
    }

    @Test
    void getBackgroundColor() {
        String color = config.getBackgroundColor();
        Assertions.assertEquals(color, config.getBackgroundColor());
    }

    @Test
    void setBackgroundColor() {
        String newColor = "YELLOW";
        config.setBackgroundColor("YELLOW");
        Assertions.assertEquals(newColor, config.getBackgroundColor());
    }

    @Test
    void getCellColor() {
        String color = config.getCellColor();
        Assertions.assertEquals(color, config.getCellColor());
    }

    @Test
    void setCellColor() {
        String newColor = "BLUE";
        config.setCellColor(newColor);
        Assertions.assertEquals(newColor, config.getCellColor());
    }

    @Test
    void getGameTitle() {
        String title = config.getGameTitle();
        Assertions.assertEquals(title, config.getGameTitle());
    }

    @Test
    void setGameTitle() {
        String newTitle = "LIFE GENERATOR 2000";
        config.setGameTitle(newTitle);
        Assertions.assertEquals(newTitle, config.getGameTitle());
    }

    @Test
    void isUseRandomDeathChance() {
        boolean randomDeathChance = config.isUseRandomDeathChance();
        if (randomDeathChance) {
            Assertions.assertTrue(config.isUseRandomDeathChance());
        } else {
            Assertions.assertFalse(config.isUseRandomDeathChance());
        }
    }

    @Test
    void setUseRandomDeathChance() {
        boolean useRandomDeathChance = true;
        config.setUseRandomDeathChance(useRandomDeathChance);
        Assertions.assertTrue(config.isUseRandomDeathChance());
    }

    @Test
    void getRandomDeathChance() {
        int randomDeathChance = config.getRandomDeathChance();
        Assertions.assertEquals(randomDeathChance, config.getRandomDeathChance());
    }

    @Test
    void setRandomDeathChance() {
        int newRandomDeathChance = 500;
        config.setRandomDeathChance(newRandomDeathChance);
        Assertions.assertEquals(newRandomDeathChance, config.getRandomDeathChance());
    }

    @Test
    void isUseRandomCellColors() {
        boolean isUseRandomColors = config.isUseRandomCellColors();
        if (isUseRandomColors) {
            Assertions.assertTrue(config.isUseRandomCellColors());
        } else {
            Assertions.assertFalse(config.isUseRandomCellColors());
        }
    }

    @Test
    void setUseRandomCellColors() {
        config.setUseRandomCellColors(false);
        Assertions.assertFalse(config.isUseRandomCellColors());
        config.setUseRandomCellColors(true);
        Assertions.assertTrue(config.isUseRandomCellColors());
    }

    @Test
    void isShowGenerationInConsole() {
        boolean showGen = config.isShowGenerationInConsole();
        if (showGen) {
            Assertions.assertTrue(config.isShowGenerationInConsole());
        } else {
            Assertions.assertFalse(config.isShowGenerationInConsole());
        }
    }

    @Test
    void setShowGenerationInConsole() {
        config.setShowGenerationInConsole(false);
        Assertions.assertFalse(config.isShowGenerationInConsole());
        config.setShowGenerationInConsole(true);
        Assertions.assertTrue(config.isShowGenerationInConsole());
    }

    @Test
    void isDisableGUI() {
        boolean disableGui = config.isGUIDisabled();
        if (disableGui) {
            Assertions.assertTrue(config.isGUIDisabled());
        } else {
            Assertions.assertFalse(config.isGUIDisabled());
        }
    }

    @Test
    void setDisableGUI() {
        config.setGuiDisabled(false);
        Assertions.assertFalse(config.isGUIDisabled());
        config.setGuiDisabled(true);
        Assertions.assertTrue(config.isGUIDisabled());
    }

    @Test
    void getConsoleCellAliveDisplay() {
        String liveCell = config.getConsoleCellAliveDisplay();
        Assertions.assertEquals(liveCell, config.getConsoleCellAliveDisplay());
    }

    @Test
    void setConsoleCellAliveDisplay() {
        String newCellDisplay = "#";
        config.setConsoleCellAliveDisplay(newCellDisplay);
        Assertions.assertEquals(newCellDisplay, config.getConsoleCellAliveDisplay());
    }

    @Test
    void getConsoleCellDeadDisplay() {
        String deadCell = config.getConsoleCellDeadDisplay();
        Assertions.assertEquals(deadCell, config.getConsoleCellDeadDisplay());
    }

    @Test
    void setConsoleCellDeadDisplay() {
        String newDeadCell = "0";
        config.setConsoleCellDeadDisplay(newDeadCell);
        Assertions.assertEquals(newDeadCell, config.getConsoleCellDeadDisplay());
    }

    @Test
    void isPlayInReverse() {
        boolean isReversed = config.isPlayInReverse();
        Assertions.assertEquals(isReversed, config.isPlayInReverse());
    }

    @Test
    void setPlayInReverse() {
        config.setPlayInReverse(true);
        Assertions.assertTrue(config.isPlayInReverse());
        config.setPlayInReverse(false);
        Assertions.assertFalse(config.isPlayInReverse());
    }
}