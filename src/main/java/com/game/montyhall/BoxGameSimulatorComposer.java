package com.game.montyhall;

public class BoxGameSimulatorComposer {
    private static BoxGameSimulatorComposer INSTANCE;

    private BoxGameSimulatorComposer() {
    }

    public static BoxGameSimulatorComposer getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new BoxGameSimulatorComposer();
        }

        return INSTANCE;
    }

    public BoxGameSimulator createMontyHallBoxGameSimulator(int numOfBoxes, int numOfRuns, String simulationName) {
        BoxGame boxGame = new MontyHallBoxGame(numOfBoxes);
        MontyHallBoxGameSimulator montyHallBoxGameSimulator = new MontyHallBoxGameSimulator(boxGame, numOfRuns, simulationName);

        return montyHallBoxGameSimulator;
    }
}
