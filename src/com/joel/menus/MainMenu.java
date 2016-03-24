package com.joel.menus;

import com.joel.MainGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * Created on 3/23/2016 by Joel Blanchette.
 * Shared Application Developer
 */
public class MainMenu extends Menu {

    public MainMenu() {
        super("Main Menu");
        sizeX = MainGame.resX;
        sizeY = MainGame.resY/6;
        x = 0;
        int heightInTiles = MainGame.resY/MainGame.tilesize;
        y = heightInTiles - heightInTiles/6;
        MenuButton button = new MenuButton("Create Stockpile",this);
        button.setX(0);
        button.setY(this.y);
        button.setImageKey("stock.png");
        buttons[0] = button;
        backGroundColor = Color.white;
    }

    @Override
    public void render(Graphics graphics) {
        super.render(graphics);
        for(int count = 0; count < buttons.length; count++) {
            buttons[count].render(graphics);
        }
    }
}
