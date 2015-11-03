package com.joel.maps;

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

    public Menu(String name) {
        this.name = name;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.fillRect(x,y,sizeX,sizeY);
    }

    @Override
    public void update(int delta) {

    }
}
