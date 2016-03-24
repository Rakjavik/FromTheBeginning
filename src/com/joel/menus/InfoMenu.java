package com.joel.menus;

import com.joel.MainGame;
import com.joel.item.Item;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created on 3/23/2016 by Joel Blanchette.
 * Shared Application Developer
 */
public class InfoMenu extends Menu {

    private Object objectInFocus = null;
    private List<InfoSection> infoSections;

    public InfoMenu() {
        super("Information Window");
        int widthInTiles = MainGame.resX/MainGame.tilesize;
        x = widthInTiles - widthInTiles/6;
        y = 0;
        sizeY = MainGame.resY - MainGame.resY/6;
        sizeX = MainGame.resX/6;
        backGroundColor = Color.blue;
        enabled = true;
        buttons = new MenuButton[0];
        infoSections = new ArrayList<InfoSection>();
    }

    @Override
    public void update(int delta) {
        super.update(delta);
        if(objectInFocus != MainGame.selected) {
            objectInFocus = MainGame.selected;
            if(objectInFocus instanceof Item) {
                InfoSection mainDisplay = new InfoSection(this);
                mainDisplay.setImageKey(((Item) objectInFocus).getImageKey());
                mainDisplay.setX(x);
                mainDisplay.setY(y);
                mainDisplay.setColor(Color.black);
                String textToDisplay = ((Item) objectInFocus).getName() + ", Stockable ? " + ((Item) objectInFocus).isStockable();
                mainDisplay.setTextToDisplay(textToDisplay);
                infoSections.add(mainDisplay);
            }
        }
    }

    @Override
    public void render(Graphics graphics) {
        super.render(graphics);
        for(InfoSection section : infoSections) {
            section.render(graphics);
        }
    }
}
