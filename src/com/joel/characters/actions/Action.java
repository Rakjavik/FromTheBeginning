package com.joel.characters.actions;

import com.joel.characters.Char;

/**
 * Created by 430009998 on 10/28/2015.
 */
public abstract class Action {

    protected Char character;
    protected String name;
    protected int direction;

    public Action(Char character) {
        this.character = character;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDirection() {
        return direction;
    }

    protected abstract void update();

    public void setDirection(int direction) {
        this.direction = direction;
    }


}
