package com.game.montyhall;

import java.util.ArrayList;
import java.util.List;

public class BoxFactory {
    public static List<Box> createBoxes(int numOfBoxes) {
        List<Box> initializedBoxes = new ArrayList<>();

        for(int ctr = 0; ctr < numOfBoxes; ctr++) {
            initializedBoxes.add(new Box(ctr+1));
        }

        return initializedBoxes;
    }
}
