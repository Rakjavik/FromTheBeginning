package com.joel.menus;

import com.joel.MainGame;
import com.joel.Renderable;
import org.newdawn.slick.Graphics;

/**
 * Created on 3/23/2016 by Joel Blanchette.
 * Shared Application Developer
 */
public class MenuButton implements Renderable {

    public static final int BUTTONSIZE = MainGame.tilesize/4;

    private int x;
    private int y;
    private String imageKey;
    private String name;
    private Menu parent;

    public MenuButton(String name,Menu parent) {
        this.name = name;
        this.parent = parent;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(MainGame.imageManager.getImage(imageKey),x*BUTTONSIZE,parent.getY()*MainGame.tilesize);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getImageKey() {
        return imageKey;
    }

    public void setImageKey(String imageKey) {
        this.imageKey = imageKey;
    }
}
