package com.game.montyhall;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@EqualsAndHashCode
public class Box {
    private int boxNumber;
    private boolean isOpen;
    @Accessors(fluent = true)
    private boolean hasPrizeMoney;

    public Box(int boxNumber) {
        this.boxNumber = boxNumber;
        this.isOpen = false;
        this.hasPrizeMoney = false;
    }

}
