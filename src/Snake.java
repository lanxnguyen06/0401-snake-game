/*
 * Created on 2025-11-21
 *
 * Copyright (c) 2025 Nadine von Frankenberg
 */

import java.util.ArrayList;

public class Snake {
    private SnakeSegment head;
    private Direction direction; 

    private boolean shouldGrow = false;

    public Snake() {
        // You may change this code for extra credit (implement some fancy stuff!)
        // Feel free to make the starting position random
        Position startingPosition = new Position(10, 10);
        head = new SnakeSegment(startingPosition);
        direction = Direction.RIGHT;
    }

    public void shouldGrow() {
        shouldGrow = true;
    }

    private void removeTail() {
        if (head.getNext() == null) {
            return;
        }

        SnakeSegment current = head; // set the current to head

        while(current.getNext().getNext() != null) { // check if the segment 2 positions ahead is not null
            current = current.getNext(); // if true set current to next node
        }
        current.setNext(null); // removes last node (tail)
    }

    // Returns true if the snake is colliding with itself
    public boolean isColliding() {
        if (isBodyPartAt(head.getPosition())) {
            return true;
        }

        // optional extra credit collision on the walls
        int maxX = SnakeGame.WIDTH / SnakeGame.SQUARE_SIZE; // snakegame.width is the screen width while square_size is the size of each square where the snake moves in. dividing them will give the amount of squares horizontally
        int maxY = SnakeGame.HEIGHT / SnakeGame.SQUARE_SIZE; // same thing here, gives the amount of squares vertically
        if (head.getPosition().x < 0 || head.getPosition().x >= maxX || head.getPosition().y < 0 || head.getPosition().y >= maxY){
            return true;
        }

        return false;
    }

    public boolean isBodyPartAt(Position position) {
        SnakeSegment current = head.getNext(); // set the current to the next node
        while(current != null) {
            if (current.getPosition().equals(position)) {  // checks if there's a body part at this position
                return true; // collision
            }
            current = current.getNext();
        }
        return false; // no collision
    }

    // Sets the direction the snake will move in
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    // Gets the length of the snake
    public int getLength() {
        int count = 0;
        SnakeSegment current = head;
        while(current != null) { // 
            count ++; 
            current = current.getNext();
        }
        return count;
    }

    // Moves the snake by one in the next direction
    // we added the optional collision method to isColliding() instead because we wanted the game over screen to show up
    public void move() {
        if (direction == null) {
            return;
        }
        Position newPosition = head.getPosition().add(direction.deltaPosition()); // gets the current position of the head and adds a direction to it so it can move

        SnakeSegment newHead = new SnakeSegment (newPosition); // snake's new head
        newHead.setNext(head); // new head points to old head
        head = newHead; // updates head to new head

        if (!shouldGrow) {
            removeTail(); // if it's not growing remove the tail so when head is added it looks like it stays the same length
        } else {
            shouldGrow = false; 
        }
    }

    // Return the head of the snake
    public SnakeSegment getHead() {
        return this.head;
    }

    // Returns the start of the snake's body (NOT the head!)
    public SnakeSegment getBody() {
        return head.getNext();
    }

    // OPTIONAL: Implement an algorithm that moves the food for us
    public Direction findNextMove(ArrayList<Food> food) {
        return null;
    }
}

class SnakeSegment {
    private Position position;
    private SnakeSegment next;

    public SnakeSegment(Position pos) {
        this.position = pos;
    }

    public Position getPosition() {
        return this.position;
    }

    public SnakeSegment getNext() {
        return this.next;
    }

    public void setNext(SnakeSegment next) {
        this.next = next;
    }
}