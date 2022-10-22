package com.justinmtech.gameoflife.config;

import com.justinmtech.gameoflife.generation.GenerationType;

import java.util.Arrays;

public class GameConfig {
    private GenerationType generator;
    private String gameTitle;
    private int updateDelay;
    private int generationChance;
    private boolean useRandomDeathChance;
    private int randomDeathChance;
    private int height;
    private int width;
    private int maxGeneration;
    private int[] seed;
    private boolean useSeed;
    private String backgroundColor;
    private String cellColor;
    private boolean useRandomCellColors;
    private boolean disableGUI;
    private boolean showGenerationInConsole;
    private String consoleCellAliveDisplay;
    private String consoleCellDeadDisplay;

    public GameConfig() {}

    public GenerationType getGenerator() {
        return generator;
    }

    public void setGenerator(GenerationType type) {
        this.generator = type;
    }

    public int getUpdateDelay() {
        return updateDelay;
    }

    public void setUpdateDelay(int updateDelay) {
        this.updateDelay = updateDelay;
    }

    public int getGenerationChance() {
        return generationChance;
    }

    public void setGenerationChance(int generationChance) {
        this.generationChance = generationChance;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int[] getSeed() {
        return seed;
    }

    public void setSeed(int[] seed) {
        this.seed = seed;
    }

    public int getMaxGeneration() {
        return maxGeneration;
    }

    public void setMaxGeneration(int maxGeneration) {
        this.maxGeneration = maxGeneration;
    }

    public boolean isUseSeed() {
        return useSeed;
    }

    public void setUseSeed(boolean useSeed) {
        this.useSeed = useSeed;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundGameColor) {
        this.backgroundColor = backgroundGameColor;
    }

    public String getCellColor() {
        return cellColor;
    }

    public void setCellColor(String cellColor) {
        this.cellColor = cellColor;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public boolean isUseRandomDeathChance() {
        return useRandomDeathChance;
    }

    public void setUseRandomDeathChance(boolean useRandomDeathChance) {
        this.useRandomDeathChance = useRandomDeathChance;
    }

    public int getRandomDeathChance() {
        return randomDeathChance;
    }

    public void setRandomDeathChance(int randomDeathChance) {
        this.randomDeathChance = randomDeathChance;
    }

    public boolean isUseRandomCellColors() {
        return useRandomCellColors;
    }

    public void setUseRandomCellColors(boolean useRandomCellColors) {
        this.useRandomCellColors = useRandomCellColors;
    }

    public boolean isShowGenerationInConsole() {
        return showGenerationInConsole;
    }

    public void setShowGenerationInConsole(boolean showGenerationInConsole) {
        this.showGenerationInConsole = showGenerationInConsole;
    }

    public boolean isDisableGUI() {
        return disableGUI;
    }

    public void setDisableGUI(boolean disableGUI) {
        this.disableGUI = disableGUI;
    }

    public String getConsoleCellAliveDisplay() {
        return consoleCellAliveDisplay;
    }

    public void setConsoleCellAliveDisplay(String consoleCellAliveDisplay) {
        this.consoleCellAliveDisplay = consoleCellAliveDisplay;
    }

    public String getConsoleCellDeadDisplay() {
        return consoleCellDeadDisplay;
    }

    public void setConsoleCellDeadDisplay(String consoleCellDeadDisplay) {
        this.consoleCellDeadDisplay = consoleCellDeadDisplay;
    }

    @Override
    public String toString() {
        return "#generator types static/dynamic" +
                "\ngenerator: " + generator +
                "\nupdateDelay: " + updateDelay +
                "\ngenerationChance: " + generationChance +
                "\nmaxGenerations: " + maxGeneration +
                "\nuseRandomDeathChance: " + useRandomDeathChance +
                "\nrandomDeathChance: " + randomDeathChance +
                "\nheight: " + height +
                "\nwidth: " + width +
                "\nseed: " + Arrays.toString(seed) +
                "\nuseSeed: " + useSeed +
                "\nbackgroundColor: " + backgroundColor +
                "\ncellColor: " + cellColor +
                "\nuseRandomCellColors: " + useRandomCellColors +
                "\nshowGenerationInConsole: " + showGenerationInConsole +
                "\ndisableGUI: " + disableGUI +
                "\nconsoleCellAliveDisplay: " + consoleCellAliveDisplay +
                "\nconsoleCellDeadDisplay: " + consoleCellDeadDisplay +
                "\ngameTitle: " + gameTitle;
    }
}
