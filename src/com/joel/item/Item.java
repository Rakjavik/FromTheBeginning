package com.joel.item;

import com.joel.MainGame;
import com.joel.Renderable;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 * Created by 430009998 on 11/1/2015.
 */
public class Item implements Renderable {
    protected String name;
    protected boolean render;
    protected Image image;
    protected int x = 0;
    protected int y = 0;
    protected boolean stored = false;
    protected boolean selected = false;
    public Item(String name) {
        this.name = name;
        render = false;
    }

    @Override
    public void render(Graphics graphics) {
        if (render) {
            graphics.drawImage(image, x * MainGame.tilesize - MainGame.viewX, y * MainGame.tilesize - MainGame.viewY);
            if(selected) {
                graphics.setColor(Color.red);
                graphics.drawRect(x * MainGame.tilesize - MainGame.viewX, y * MainGame.tilesize - MainGame.viewY,MainGame.tilesize,MainGame.tilesize);
            }
        }
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

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
