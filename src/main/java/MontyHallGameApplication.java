import com.game.montyhall.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class MontyHallGameApplication {
    private static final int NUM_OF_BOXES = 3;
    private static final int NUM_OF_RUNS = 100000;
    private static final String simulationNameWithSwitching = "MONTY HALL WITH SWITCHING";
    private static final String simulationNameWithoutSwitching = "MONTY HALL WITHOUT SWITCHING";


    public static void main(String[] args) {
        BoxGameSimulator simulatorWithoutSwitching = BoxGameSimulatorFactory.createMontyHallBoxGameSimulator(NUM_OF_BOXES, NUM_OF_RUNS, simulationNameWithoutSwitching);
        BoxGameSimulator simulatorWithSwitching = BoxGameSimulatorFactory.createMontyHallBoxGameSimulator(NUM_OF_BOXES, NUM_OF_RUNS, simulationNameWithSwitching);

        CompletableFuture<Void> simulationFuture = CompletableFuture.runAsync(() -> simulatorWithoutSwitching.runSimulation(false));
        CompletableFuture<Void> simulationWithSwitchingFuture = CompletableFuture.runAsync(() -> simulatorWithSwitching.runSimulation(true));

        CompletableFuture.allOf(simulationFuture, simulationWithSwitchingFuture).join();

        simulatorWithoutSwitching.displayResults();
        simulatorWithSwitching.displayResults();
    }
}
