package com.game.montyhall;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

@ExtendWith(MockitoExtension.class)
class MontyHallBoxGameTest {

    private static final int NUM_OF_BOXES = 3;

    @Mock
    private Box selectedBox, revealedBox, switchedBox, prizeMoneyBox;


    MontyHallBoxGame boxGame = new MontyHallBoxGame(NUM_OF_BOXES);

    @BeforeEach
    void setup() {
        Random random = new Random();
        boxGame.selectBox(random.nextInt(NUM_OF_BOXES));
        selectedBox = boxGame.getSelectedBox();

        prizeMoneyBox = boxGame.getPrizeBox();

        boxGame.revealBox();
        revealedBox = boxGame.getRevealedBox();

        boxGame.switchBox();
        switchedBox = boxGame.getSelectedBox();


    }

    @Test
    void testSelectedBoxIsDifferentFromSwitchedBox() {
        Assertions.assertNotEquals(selectedBox, switchedBox);
    }

    @Test
    void testSelectedBoxIsDifferentFromRevealedBox() {
        Assertions.assertNotEquals(selectedBox, revealedBox);
    }

    @Test
    void testRevealedBoxIsNotPrizeMoneyBox() {
        Assertions.assertNotEquals(revealedBox, prizeMoneyBox);
    }

}
