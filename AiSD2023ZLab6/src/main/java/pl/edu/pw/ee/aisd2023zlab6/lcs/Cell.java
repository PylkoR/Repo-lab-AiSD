package pl.edu.pw.ee.aisd2023zlab6.lcs;

import static pl.edu.pw.ee.aisd2023zlab6.lcs.Cell.Direction.NONE;

public class Cell {
    public enum Direction {
        LEFT,
        TOP,
        DIAGONAL,
        NONE
    }

    int value;
    Direction direction;

    public Cell() {
        value = 0;
        direction = NONE;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getValue() {
        return value;
    }

    public Direction getDirection() {
        return direction;
    }
}