package com.joel.characters.actions;

import com.joel.MainGame;
import com.joel.characters.Char;
import com.joel.maps.Map;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by 430009998 on 10/28/2015.
 */
public class WonderingEvent extends Event {

    public WonderingEvent(Char character) {
        super("Wondering",character);
        setSpeed(200);
    }

    @Override
    public void update() {
        Map map = MainGame.map;
        MoveAction moveAction = new MoveAction(character);
        int direction = ThreadLocalRandom.current().nextInt(0,4);
        moveAction.setNewPosition(new Point(0,0));
        if (direction == Event.DIRECTION_DOWN) {
            int tileID = map.getTiledMap().getTileId(character.getX(), character.getY() + 1, 0);
            if (!Boolean.parseBoolean(map.getTiledMap().getTileProperty(tileID, "solid", "false")) && character.getY() < map.getTiledMap().getHeight()-2) {
                moveAction.setNewPosition(new Point(0, 1));
            }
        } else if (direction == Event.DIRECTION_LEFT) {
            int tileID = map.getTiledMap().getTileId(character.getX() - 1, character.getY(), 0);
            if (!Boolean.parseBoolean(map.getTiledMap().getTileProperty(tileID, "solid", "false")) && character.getX() > 1) {
                moveAction.setNewPosition(new Point(-1, 0));
            }

        } else if (direction == Event.DIRECTION_UP) {
            int tileID = map.getTiledMap().getTileId(character.getX(), character.getY() - 1, 0);
            if (!Boolean.parseBoolean(map.getTiledMap().getTileProperty(tileID, "solid", "false")) && character.getY() > 1) {
                moveAction.setNewPosition(new Point(0, -1));
            }
        } else if (direction == Event.DIRECTION_RIGHT) {
            int tileID = map.getTiledMap().getTileId(character.getX() + 1, character.getY(), 0);
            if (!Boolean.parseBoolean(map.getTiledMap().getTileProperty(tileID, "solid", "false")) && character.getX() < map.getTiledMap().getWidth() - 2) {
                moveAction.setNewPosition(new Point(1, 0));
            }

        }
        moveAction.setDirection(direction);
        moveAction.update();
    }
}
