/*
 * Created on 2025-11-21
 *
 * Copyright (c) 2025 Nadine von Frankenberg
 */

public enum Direction {
    DOWN,
    RIGHT,
    LEFT,
    UP;

    public Position deltaPosition() {
        // TODO: should return the new position after the snake has moved
        // The position should either move +1 or -1 along the x or y axis
        switch(this){
            case DOWN:
                return new Position(0, 1);
            case RIGHT:
                return new Position (1, 0);
            case LEFT:
                return new Position(-1, 0);
            case UP:
                return new Position(0, -1);
            default:
                return new Position(0, 0); // default position if not moving
        }
    }
}
