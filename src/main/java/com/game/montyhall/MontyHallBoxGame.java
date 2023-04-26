package com.game.montyhall;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private Box revealedBox;
    private Random random;

    public MontyHallBoxGame(List<Box> boxes) {
        this.numOfBoxes = boxes.size();
        this.boxes = boxes;
        this.random = new Random();
    }

    public void resetGame() {
        this.boxes.forEach(box -> box.resetState());
        this.boxes.get(random.nextInt(numOfBoxes)).setHasPrizeMoney(true);
    }

    public Box getPrizeBox(List<Box> boxes) {
        return boxes.stream()
                .filter(box -> box.hasPrizeMoney())
                .findFirst()
                .get();
    }

    @Override
    public void selectBox(int boxIndex) {
        setSelectedBox(boxes.get(boxIndex));
    }

    @Override
    public void switchBox() {
        //choose the box that is not already selected and revealed
        List<Box> qualifiedBoxesToSwitchTo = boxes.stream()
                .filter(box -> !selectedBox.equals(box) && !box.isOpen())
                .collect(Collectors.toList());

        Box newSelectedBox = qualifiedBoxesToSwitchTo.get(random.nextInt(qualifiedBoxesToSwitchTo.size()));
        setSelectedBox(newSelectedBox);
        logger.debug("You switched to box number: {}", getSelectedBox().getBoxNumber());
    }

    @Override
    public void revealBox() {
        //choose the box that is not already selected, open, and has no prize
        List<Box> qualifiedBoxesToReveal = boxes.stream()
                .filter(box -> !selectedBox.equals(box) && !box.hasPrizeMoney() && !box.isOpen())
                .collect(Collectors.toList());

        Box revealedBox = qualifiedBoxesToReveal.get(random.nextInt(qualifiedBoxesToReveal.size()));
        revealedBox.setOpen(true);
        setRevealedBox(revealedBox);
        logger.debug("Monty reveals an empty box behind box number: {}", getRevealedBox().getBoxNumber());
    }

    @Override
    public void displayGameStatus() {
        logger.debug("The prize money is behind box: {}", getPrizeBox(this.boxes).getBoxNumber());

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
