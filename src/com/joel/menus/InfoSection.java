package com.joel.menus;

import com.joel.MainGame;
import com.joel.Renderable;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import sun.applet.Main;

/**
 * Created on 3/23/2016 by Joel Blanchette.
 * Shared Application Developer
 */
public class InfoSection implements Renderable {
    private String textToDisplay;
    private int x;
    private int y;
    private Color color;
    private String imageKey;
    private InfoMenu parent;

    public InfoSection(InfoMenu parent) {
        this.parent = parent;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(color);
        graphics.drawString(textToDisplay,x* MainGame.tilesize,y*MainGame.tilesize);
        graphics.drawImage(MainGame.imageManager.getImage(imageKey),x*MainGame.tilesize,y*MainGame.tilesize);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setImageKey(String imageKey) {
        this.imageKey = imageKey;
    }

    public void setTextToDisplay(String textToDisplay) {
        this.textToDisplay = textToDisplay;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
