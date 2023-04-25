package com.game.montyhall;

public interface BoxGame {
    void selectBox(int boxNumber);
    void switchBox();
    void revealBox();
    void displayGameStatus();
    void resetGame();
    boolean isWinner();
    int getNumOfBoxes();
}
