package com.game.montyhall;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MontyHallBoxGameTest {

    @Mock
    private Box box1, box2, box3;
    @Mock
    private Random random;

    private MontyHallBoxGame boxGame;
    private List<Box> boxes;


    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        boxes = Arrays.asList(box1, box2, box3);
        boxGame = new MontyHallBoxGame(boxes);
    }

    @Test
    void testBoxGameInitialization() {
        Assertions.assertEquals(boxes.size(), boxGame.getBoxes().size());
    }

    @Test
    void testResetGameAndVerifyHasPrizeMoney_box2() {
        int selectedBoxIndex = 1;
        int numOfBoxes = boxes.size();

        when(random.nextInt(numOfBoxes)).thenReturn(selectedBoxIndex);

        boxGame.setRandom(random);

        boxGame.resetGame();

        verify(box1).resetState();
        verify(box2).resetState();
        verify(box3).resetState();

        verify(box2).setHasPrizeMoney(true);
    }

    @Test
    void testGetPrizeBox_box3() {
        when(box1.hasPrizeMoney()).thenReturn(false);
        when(box2.hasPrizeMoney()).thenReturn(false);
        when(box3.hasPrizeMoney()).thenReturn(true);

        Box resultBox = boxGame.getPrizeBox(boxes);

        verify(box1).hasPrizeMoney();
        verify(box2).hasPrizeMoney();
        verify(box3).hasPrizeMoney();

        Assertions.assertEquals(box3, resultBox);

        Assertions.assertNotEquals(box1, resultBox);
        Assertions.assertNotEquals(box2, resultBox);
    }

    @Test
    void testSelectBox_box2() {
        int selectedBoxIndex = 1;
        boxGame.selectBox(selectedBoxIndex);
        Box resultBox = boxGame.getSelectedBox();

        Assertions.assertEquals(box2, resultBox);

        Assertions.assertNotEquals(box1, resultBox);
        Assertions.assertNotEquals(box3, resultBox);
    }

    @Test
    void testSwitchBox_box1_box3() {
        int selectedBoxIndex = 0;
        boxGame.selectBox(selectedBoxIndex);
        Box initialSelectedBox = boxGame.getSelectedBox();

        when(box2.isOpen()).thenReturn(true);

        boxGame.switchBox();

        Box finalSelectedBox = boxGame.getSelectedBox();

        Assertions.assertEquals(box1, initialSelectedBox);
        Assertions.assertEquals(box3, finalSelectedBox);
    }

    @Test
    void testRevealBox_box2() {
        int selectedBoxIndex = 0;
        boxGame.selectBox(selectedBoxIndex);

        when(box1.hasPrizeMoney()).thenReturn(false);
        when(box2.hasPrizeMoney()).thenReturn(false);
        when(box3.hasPrizeMoney()).thenReturn(true);

        when(box1.isOpen()).thenReturn(false);
        when(box2.isOpen()).thenReturn(false);
        when(box3.isOpen()).thenReturn(false);

        boxGame.revealBox();

        Box revealedBox = boxGame.getRevealedBox();

        Assertions.assertEquals(box2, revealedBox);
    }

    @Test
    void testIsWinner_box1() {
        int selectedBoxIndex = 0;
        boxGame.selectBox(selectedBoxIndex);

        when(box1.hasPrizeMoney()).thenReturn(true);

        boolean doesBox1HavePrizeMoney = boxGame.isWinner();

        Assertions.assertTrue(doesBox1HavePrizeMoney);
    }

}
