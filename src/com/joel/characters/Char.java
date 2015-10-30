package com.joel.characters;

import com.joel.MainGame;
import com.joel.Renderable;
import com.joel.Updateable;
import com.joel.characters.actions.Event;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.util.pathfinding.Mover;

/**
 * Created by 430009998 on 10/28/2015.
 */
public abstract class Char implements Updateable,Renderable,Mover {

    public static final int NUM_OF_ANIMATIONS = 8;

    public static final int WALK_LEFT = 0;
    public static final int WALK_UP = 1;
    public static final int WALK_RIGHT = 2;
    public static final int WALK_DOWN = 3;
    public static final int STAND_LEFT = 4;
    public static final int STAND_UP = 5;
    public static final int STAND_RIGHT = 6;
    public static final int STAND_DOWN = 7;


    protected int x;
    protected int y;
    protected int notUpdatedFor;
    protected Event currentEvent;
    protected int direction;
    protected String name;
    protected SpriteSheet spriteSheet;
    protected Animation[] animations;
    protected int currentAnimation;
    protected int walkAnimationSpeed;

    public Char(String name) {
        this.name = name;
        x = 12;
        y = 10;
        notUpdatedFor = 1;
        direction = Event.DIRECTION_DOWN;
        currentAnimation = WALK_UP;
        animations = new Animation[NUM_OF_ANIMATIONS];
    }

    @Override
    public void update(int delta) {
        notUpdatedFor += delta;
        if (notUpdatedFor > currentEvent.getSpeed()) {
            int currentDirection = direction;
            currentEvent.update();
            if(currentDirection != direction) {
                currentAnimation = direction;
            }
            notUpdatedFor = 0;
        }
    }

    @Override
    public void render(Graphics graphics) {
        animations[currentAnimation].draw(x* MainGame.tilesize,y*MainGame.tilesize);
    }


    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCurrentEvent(Event currentEvent) {
        this.currentEvent = currentEvent;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public void setCurrentAnimation(int currentAnimation) {
        this.currentAnimation = currentAnimation;
    }
}