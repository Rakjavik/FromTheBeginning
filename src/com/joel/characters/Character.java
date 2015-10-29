package com.joel.characters;

import com.joel.MainGame;
import com.joel.Renderable;
import com.joel.Updateable;
import com.joel.characters.actions.Event;
import com.joel.characters.actions.Action;
import com.joel.characters.actions.MoveAction;
import com.joel.characters.actions.WonderingEvent;
import com.joel.maps.Map;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 * Created by 430009998 on 10/28/2015.
 */
public abstract class Character implements Updateable,Renderable {

    protected int x;
    protected int y;
    protected Image image;
    protected int notUpdatedFor;
    protected Event currentEvent;
    protected int direction;
    protected Rectangle box;
    protected String name;

    public Character(String name) {
        this.name = name;
        currentEvent = new WonderingEvent();
        x = 200;
        y = 200;
        notUpdatedFor = 1;
        direction = Event.DIRECTION_DOWN;

    }

    @Override
    public void update(int delta) {
        notUpdatedFor += delta;
        if (notUpdatedFor > currentEvent.getSpeed()) {
            Action response = currentEvent.update();
            if (response instanceof MoveAction) {

                if (!doesCharacterInterceptWithMap(MainGame.map,new Rectangle(box.getX()+ ((MoveAction) response).getNewPosition().x
                ,box.getY()+ ((MoveAction) response).getNewPosition().y,box.getWidth(),box.getHeight()))) {
                    x += ((MoveAction) response).getNewPosition().x;
                    y += ((MoveAction) response).getNewPosition().y;
                    direction = response.getDirection();
                } else {
                    System.out.println("BANG!");
                }
            }
            box.setX(x);
            box.setY(y);
            notUpdatedFor = 0;

        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(image,x,y);
    }

    private boolean doesCharacterInterceptWithMap(Map map,Shape characterBox) {
        for (Shape shape : map.getCollisionPoints()) {
             if(shape.intersects(characterBox)) {
                 return true;
             }
        }
        return false;
    }

    public String getName() {
        return name;
    }
}
