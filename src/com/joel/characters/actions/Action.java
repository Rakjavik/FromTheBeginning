package com.joel.characters.actions;

/**
 * Created by 430009998 on 10/28/2015.
 */
public abstract class Action {
    protected String name;
    protected int direction;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
