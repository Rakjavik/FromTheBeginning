package com.joel.characters.actions;

import com.joel.characters.Char;

/**
 * Created by 430009998 on 10/29/2015.
 */
public class PlaceAction extends Action {

    private int x;
    private int y;
    private int direction;

    public PlaceAction(Char character,int x, int y,int direction) {
        super(character);
        name = "Place Action";
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    @Override
    protected void update() {
        character.setX(x);
        character.setY(y);
        character.setDirection(direction);
    }
}
