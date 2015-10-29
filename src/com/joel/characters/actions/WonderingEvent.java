package com.joel.characters.actions;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by 430009998 on 10/28/2015.
 */
public class WonderingEvent extends Event {

    public WonderingEvent() {
        super("Wondering");
        setSpeed(100);
    }

    @Override
    public Action update() {
        MoveAction moveAction = new MoveAction();
        int direction = ThreadLocalRandom.current().nextInt(0,4);
        if (direction == Event.DIRECTION_DOWN) {
            moveAction.setNewPosition(new Point(0, 25));
        } else if (direction == Event.DIRECTION_LEFT) {
            moveAction.setNewPosition(new Point(-25, 0));
        } else if (direction == Event.DIRECTION_RIGHT) {
            moveAction.setNewPosition(new Point(25, 0));
        } else if (direction == Event.DIRECTION_UP) {
            moveAction.setNewPosition(new Point(0, -25));
        }
        moveAction.setDirection(Event.DIRECTION_DOWN);
        return moveAction;
    }
}
