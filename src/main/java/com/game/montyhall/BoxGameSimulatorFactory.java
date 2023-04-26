package com.game.montyhall;

import java.util.List;

public class BoxGameSimulatorFactory {
    public static BoxGameSimulator createMontyHallBoxGameSimulator(int numOfBoxes, int numOfRuns, String simulationName) {
        List<Box> boxes = BoxFactory.createBoxes(numOfBoxes);
        BoxGame boxGame = new MontyHallBoxGame(boxes);
        BoxGameSimulator montyHallBoxGameSimulator = new MontyHallBoxGameSimulator(boxGame, numOfRuns, simulationName);

        return montyHallBoxGameSimulator;
    }
}
