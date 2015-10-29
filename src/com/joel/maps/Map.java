package com.joel.maps;

import com.joel.MainGame;
import com.joel.Renderable;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by 430009998 on 10/28/2015.
 */
public class Map implements Renderable, Serializable {
    protected Shape[] collisionPoints;
    protected String name;
    protected Image background;

    public Map(String name, String bgLocation) {
        this.name = name;
        try {
            background = new Image(bgLocation).getScaledCopy(640,480);
        } catch (SlickException e) {
            e.printStackTrace(System.out);
        }
        collisionPoints = generateLevelsCollisions();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(background,0,0);
    }

    private Shape[] generateLevelsCollisions() {
        List<Shape> shapeList = new LinkedList<Shape>();
        if (name.equals("Berry Garden")) {
            Rectangle leftWall = new Rectangle(0,0, MainGame.tilesize,480);
            Rectangle bottomWall = new Rectangle(0,480-MainGame.tilesize,640,MainGame.tilesize);
            Rectangle rightWall = new Rectangle(640-MainGame.tilesize,0,MainGame.tilesize,480);
            Rectangle topWall = new Rectangle(0,0,640,MainGame.tilesize);
            shapeList.add(leftWall);
            shapeList.add(bottomWall);
            shapeList.add(rightWall);
            shapeList.add(topWall);

        }
        Shape[] returnArray = new Shape[shapeList.size()];
        returnArray = shapeList.toArray(returnArray);
        return returnArray;
    }

    public Shape[] getCollisionPoints() {
        return collisionPoints;
    }
}
