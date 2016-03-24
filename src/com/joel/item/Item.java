package com.joel.item;

import com.joel.MainGame;
import com.joel.Renderable;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * Created by 430009998 on 11/1/2015.
 */
public class Item implements Renderable, Cloneable {
    protected String name;
    protected boolean render;
    protected String imageKey;
    protected int x = 0;
    protected int y = 0;
    protected boolean stored = false;
    protected boolean selected = false;
    protected boolean available = true;
    protected int stockType = -1;
    protected boolean stockable = false;
    protected int stackSizeMax = 1;
    protected int stackSize = 1;
    protected boolean choppable = false;
    protected int weight = 0;

    public Item(String name) {
        this.name = name;
        render = false;
    }

    @Override
    public void render(Graphics graphics) {
        if (render) {
            graphics.drawImage(MainGame.imageManager.getImage(imageKey), x * MainGame.tilesize - MainGame.viewX, y * MainGame.tilesize - MainGame.viewY);
            if(selected) {
                graphics.setColor(Color.red);
                graphics.drawRect(x * MainGame.tilesize - MainGame.viewX, y * MainGame.tilesize - MainGame.viewY,MainGame.tilesize,MainGame.tilesize);
            }
        }
    }

    public Object clone()  {
        try {
            return super.clone();
        } catch(CloneNotSupportedException ex){ex.printStackTrace();}
        return null;
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

    public String getName() {
        return name;
    }

    public boolean isStored() {
        return stored;
    }

    public void setStored(boolean stored) {
        this.stored = stored;
    }

    public boolean isRender() {
        return render;
    }

    public void setRender(boolean render) {
        this.render = render;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getStockType() {
        return stockType;
    }

    public boolean isStockable() {
        return stockable;
    }

    public boolean isChoppable() {
        return choppable;
    }

    public int getStackSizeMax() { return stackSizeMax; }

    public int getStackSize() {
        return stackSize;
    }

    public void setStackSize(int stackSize) {
        this.stackSize = stackSize;
    }

    public String getImageKey() {
        return imageKey;
    }
}
