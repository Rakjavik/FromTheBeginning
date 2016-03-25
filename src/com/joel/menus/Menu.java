package com.joel.menus;

import com.joel.MainGame;
import com.joel.Renderable;
import com.joel.Updateable;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

/**
 * Created by 430009998 on 11/2/2015.
 */
public class Menu implements Renderable,Updateable{
    protected int sizeXInTiles;
    protected int sizeYInTiles;
    protected int xInTiles;
    protected int yInTiles;
    protected String name;
    protected MenuButton[] buttons = new MenuButton[1];
    protected Color backGroundColor = Color.black;
    protected int minimumSizeInTiles;

    public Menu(String name) {
        this.name = name;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(backGroundColor);
        graphics.fillRect(xInTiles * MainGame.tilesize, yInTiles *MainGame.tilesize, sizeXInTiles *MainGame.tilesize, sizeYInTiles *MainGame.tilesize);
    }

    @Override
    public void update(int delta) {

    }

    public int getSizeXInTiles() {
        return sizeXInTiles;
    }

    public void setSizeXInTiles(int sizeXInTiles) {
        if(sizeXInTiles >= minimumSizeInTiles)
            this.sizeXInTiles = sizeXInTiles;
    }

    public int getSizeYInTiles() {
        return sizeYInTiles;
    }

    public void setSizeYInTiles(int sizeYInTiles) {
        if(sizeYInTiles >= minimumSizeInTiles)
            this.sizeYInTiles = sizeYInTiles;
    }

    public int getxInTiles() {
        return xInTiles;
    }

    public void setxInTiles(int xInTiles) {
        this.xInTiles = xInTiles;
    }

    public int getyInTiles() {
        return yInTiles;
    }

    public void setyInTiles(int yInTiles) {
        this.yInTiles = yInTiles;
    }

    public String getName() {
        return name;
    }

    public MenuButton[] getButtons() {
        return buttons;
    }

}
