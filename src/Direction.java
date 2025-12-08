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
        switch(this){
            case DOWN:
                return new Position(0, 1); // positive 1 because (0, 0) starts in the bottom left corner, so going down would mean it's increasing value
            case RIGHT:
                return new Position (1, 0);
            case LEFT:
                return new Position(-1, 0); 
            case UP:
                return new Position(0, -1); // negative 1 because (0, 0) starts in the bottom left corner, so going up would mean it's decreasing
            default:
                return new Position(0, 0); // default position if not moving
        }
    }
}
