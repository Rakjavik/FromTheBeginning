package com.joel.characters.actions;

import com.joel.characters.Char;

import java.awt.*;

/**
 * Created by 430009998 on 10/28/2015.
 */
public class MoveAction extends Action {
    private Point newPosition;

    public MoveAction(Char character) {
        super(character);
        name = "Move Action";
    }

    public Point getNewPosition() {
        return newPosition;
    }

    public void setNewPosition(Point newPosition) {
        this.newPosition = newPosition;
    }

    @Override
    protected void update() {
        character.setX(character.getX() + newPosition.x);
        character.setY(character.getY() + newPosition.y);
        character.setDirection(direction);
        character.setCurrentAnimation(direction);
    }
}
