package com.game.montyhall;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class MontyHallBoxGameSimulator implements BoxGameSimulator{
    private static final Logger logger = LoggerFactory.getLogger(MontyHallBoxGameSimulator.class);
    private BoxGame boxGame;
    private int numOfBoxes;
    private int numOfRuns;
    private int numOfWins;
    private String simulationName;
    private Random random;

    public MontyHallBoxGameSimulator(BoxGame boxGame, int numOfRuns, String simulationName) {
        this.boxGame = boxGame;
        this.numOfBoxes = boxGame.getNumOfBoxes();
        this.numOfRuns = numOfRuns;
        this.simulationName = simulationName;

        numOfWins = 0;
        random = new Random();
    }

    public void executeMontyHallGame(int numOfRuns, boolean shouldSwitch) {
        for(int ctr = 0; ctr < numOfRuns; ctr++) {
            simulateOneMontyHallGame(shouldSwitch);
        }
    }

    private void simulateOneMontyHallGame(boolean shouldSwitch) {
        int selectedBox = new Random().nextInt(numOfBoxes);

        boxGame.selectBox(selectedBox);
        int boxNumber = selectedBox + 1;
        logger.debug("You selected box number: {}", boxNumber);

        boxGame.revealBox();

        if(shouldSwitch) {
            boxGame.switchBox();
        }

        boxGame.displayGameStatus();

        if(boxGame.isWinner()) {
                numOfWins++;
        }

        boxGame.resetGame();
    }

    public void runSimulation(boolean shouldSwitch) {
        executeMontyHallGame(this.numOfRuns, shouldSwitch);
    }

    public void displayResults() {
        logger.info("The Simulation Results for {} are in!", simulationName);
        logger.info("Number of boxes: {}", numOfBoxes);
        logger.info("Number of runs: {}", numOfRuns);

        logger.info("Number of wins by this simulation ({}): {}",simulationName, numOfWins);

        double winPercentageForThisSimulation = ((double) numOfWins / numOfRuns) * 100;
        logger.info("Percentage of winning of this simulation ({}): {} %", simulationName, winPercentageForThisSimulation);
    }
}
