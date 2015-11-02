package com.joel.characters.actions;

import com.joel.characters.Char;

/**
 * Created by 430009998 on 10/29/2015.
 */
public class WaitEvent extends Event {
    private Event pausedEvent;
    private int cycles;
    private int count;

    public WaitEvent(Char character,Event pausedEvent,int cycles) {
        super("Waiting", character);
        this.pausedEvent = pausedEvent;
        this.cycles = cycles;
        count = 0;
        setSpeed(200-character.getSpeed());
    }

    @Override
    public void update() {
        count++;
        if(count == cycles) {
            if (pausedEvent != null) {
                character.setCurrentEvent(pausedEvent);
            } else {
                character.setCurrentEvent(new IdleEvent(character));
            }
        }
    }
}
