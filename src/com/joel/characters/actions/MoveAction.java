package com.joel.characters.actions;

import java.awt.*;

/**
 * Created by 430009998 on 10/28/2015.
 */
public class MoveAction extends Action {
    private Point newPosition;

    public MoveAction() {
        name = "Move Action";
    }

    public Point getNewPosition() {
        return newPosition;
    }

    public void setNewPosition(Point newPosition) {
        this.newPosition = newPosition;
    }
}
