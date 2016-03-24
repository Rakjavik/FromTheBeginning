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
    protected int sizeX;
    protected int sizeY;
    protected int x;
    protected int y;
    protected String name;
    protected Font font;
    protected boolean enabled = true;
    protected MenuButton[] buttons = new MenuButton[1];
    protected Color backGroundColor = Color.black;

    public Menu(String name) {
        this.name = name;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(backGroundColor);
        graphics.fillRect(x* MainGame.tilesize,y*MainGame.tilesize,sizeX,sizeY);
    }

    @Override
    public void update(int delta) {

    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
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

    public String getName() {
        return name;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public MenuButton[] getButtons() {
        return buttons;
    }

    public void setBackGroundColor(Color backGroundColor) {
        this.backGroundColor = backGroundColor;
    }
}
