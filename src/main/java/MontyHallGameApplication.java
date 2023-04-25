import com.game.montyhall.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class MontyHallGameApplication {
    private static final Logger logger = LoggerFactory.getLogger(MontyHallGameApplication.class);
    private static final int NUM_OF_BOXES = 3;
    private static final int NUM_OF_RUNS = 10000;
    private static final String simulationNameWithSwitching = "MONTY HALL WITH SWITCHING";
    private static final String simulationNameWithoutSwitching = "MONTY HALL WITHOUT SWITCHING";


    public static void main(String[] args) {
        BoxGameSimulator simulatorWithoutSwitching = BoxGameSimulatorComposer.getInstance().createMontyHallBoxGameSimulator(NUM_OF_BOXES, NUM_OF_RUNS, simulationNameWithoutSwitching);
        BoxGameSimulator simulatorWithSwitching = BoxGameSimulatorComposer.getInstance().createMontyHallBoxGameSimulator(NUM_OF_BOXES, NUM_OF_RUNS, simulationNameWithSwitching);

        CompletableFuture<Void> simulationFuture = CompletableFuture.runAsync(() -> simulatorWithoutSwitching.runSimulation(false));
        CompletableFuture<Void> simulationWithSwitchingFuture = CompletableFuture.runAsync(() -> simulatorWithSwitching.runSimulation(true));

        CompletableFuture.allOf(simulationFuture, simulationWithSwitchingFuture).join();

//        simulator.runSimulation(false);
//        simulatorWithSwitching.runSimulation(true);

        simulatorWithoutSwitching.displayResults();
        simulatorWithSwitching.displayResults();
    }
}
