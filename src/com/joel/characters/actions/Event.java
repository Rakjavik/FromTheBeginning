package com.joel.characters.actions;

/**
 * Created by 430009998 on 10/28/2015.
 */
public abstract class Event implements ActionInterface {

    public static int DIRECTION_LEFT = 0;
    public static int DIRECTION_UP = 1;
    public static int DIRECTION_RIGHT = 2;
    public static int DIRECTION_DOWN = 3;

    private String name;
    private int priority;
    private int speed;


    public Event(String name) {
        this.name = name;
    }


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
