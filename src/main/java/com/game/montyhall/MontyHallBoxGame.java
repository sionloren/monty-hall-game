package com.game.montyhall;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Getter
@Setter
public class MontyHallBoxGame implements BoxGame {
    private static final Logger logger = LoggerFactory.getLogger(MontyHallBoxGame.class);

    private List<Box> boxes;
    private int numOfBoxes;
    private Box selectedBox;
    private Box prizeBox;
    private Box revealedBox;
    private Random random;

    public MontyHallBoxGame(int numOfBoxes) {
        this.numOfBoxes = numOfBoxes;
        this.boxes = getInitializedBoxes(numOfBoxes);
        this.random = new Random();
        this.prizeBox = this.boxes.get(random.nextInt(numOfBoxes));
        this.prizeBox.hasPrizeMoney(true);
    }

    public void resetGame() {
        this.boxes = getInitializedBoxes(this.numOfBoxes);
        this.prizeBox = this.boxes.get(random.nextInt(numOfBoxes));
        this.prizeBox.hasPrizeMoney(true);
    }

    private List<Box> getInitializedBoxes(int numOfBoxes) {
        List<Box> initializedBoxes = new ArrayList<>();

        for(int ctr = 0; ctr < numOfBoxes; ctr++) {
            initializedBoxes.add(new Box(ctr+1));
        }

        return initializedBoxes;
    }

    @Override
    public void selectBox(int boxIndex) {
        setSelectedBox(boxes.get(boxIndex));
    }

    @Override
    public void switchBox() {
        //choose the box that is not already selected or revealed
        List<Box> qualifiedBoxesToSwitchTo = boxes.stream()
                .filter(box -> !selectedBox.equals(box) && !box.isOpen())
                .collect(Collectors.toList());

        Box newSelectedBox = qualifiedBoxesToSwitchTo.get(random.nextInt(qualifiedBoxesToSwitchTo.size()));
        setSelectedBox(newSelectedBox); ;
        logger.debug("You switched to box number: {}", getSelectedBox().getBoxNumber());
    }

    @Override
    public void revealBox() {
        //choose the box that is not already selected or has no prize
        List<Box> qualifiedBoxesToReveal = boxes.stream()
                .filter(box -> !selectedBox.equals(box) && !box.hasPrizeMoney())
                .collect(Collectors.toList());

        Box revealedBox = qualifiedBoxesToReveal.get(random.nextInt(qualifiedBoxesToReveal.size()));
        revealedBox.setOpen(true);
        setRevealedBox(revealedBox);
        logger.debug("Monty reveals an empty box behind box number: {}", getRevealedBox().getBoxNumber());
    }

    @Override
    public void displayGameStatus() {
        logger.debug("The prize money is behind box: {}", getPrizeBox().getBoxNumber());

        if (isWinner()) {
            logger.debug("You chose the correct box and won the money!");
        } else {
            logger.debug("You chose the wrong box and lost.");
        }
    }

    @Override
    public boolean isWinner() {
        return getSelectedBox().hasPrizeMoney();
    }
}
