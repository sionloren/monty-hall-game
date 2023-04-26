package com.game.montyhall;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Box {
    private int boxNumber;
    private boolean isOpen;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private boolean hasPrizeMoney;

    public Box(int boxNumber) {
        this.boxNumber = boxNumber;
        this.isOpen = false;
        this.hasPrizeMoney = false;
    }

    public boolean hasPrizeMoney() {
        return hasPrizeMoney;
    }

    public void setHasPrizeMoney(boolean hasPrizeMoney) {
        this.hasPrizeMoney = hasPrizeMoney;
    }

    public void resetState() {
        this.isOpen = false;
        this.hasPrizeMoney = false;
    }

}
