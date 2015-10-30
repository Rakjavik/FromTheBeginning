package com.joel.characters.actions;

import com.joel.characters.Char;

/**
 * Created by 430009998 on 10/29/2015.
 */
public class IdleEvent extends Event {


    public IdleEvent(Char character) {
        super("Idle Event", character);
    }

    @Override
    public void update() {

    }
}
