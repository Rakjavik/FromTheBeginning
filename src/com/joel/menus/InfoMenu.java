package com.joel.menus;

import com.joel.MainGame;
import com.joel.characters.Char;
import com.joel.item.Item;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;


/**
 * Created on 3/23/2016 by Joel Blanchette.
 * Shared Application Developer
 */
public class InfoMenu extends Menu {

    private Object objectInFocus = null;
    private Map<String,InfoSection> infoSections;

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
        infoSections = new HashMap<String, InfoSection>();
        InfoSection summarySection = new InfoSection(this);
        infoSections.put("summary", summarySection);
    }

    @Override
    public void update(int delta) {
        super.update(delta);
        if(objectInFocus != MainGame.selected) {
            InfoSection mainDisplay = infoSections.get("summary");
            mainDisplay.setEnabled(true);
            objectInFocus = MainGame.selected;
            if(objectInFocus instanceof Item) {
                mainDisplay.setImageKey(((Item) objectInFocus).getImageKey());
                mainDisplay.setImgPosition(new Point(x, y));
                mainDisplay.setTxtPosition(new Point(x,y+1));
                mainDisplay.setColor(Color.black);
                mainDisplay.setFontSize(16);
                String textToDisplay = "Item - " + ((Item) objectInFocus).getName() + " \n Description: \n " + ((Item) objectInFocus).getDescription();
                mainDisplay.setTextToDisplay(textToDisplay);
            }
            if(objectInFocus instanceof Char) {
                mainDisplay.setImageKey(((Char) objectInFocus).getProtraitKey());
                mainDisplay.setImgPosition(new Point(x, y));
                mainDisplay.setTxtPosition(new Point(x,y+1));
                mainDisplay.setColor(Color.black);
                mainDisplay.setFontSize(16);
                String textToDisplay = objectInFocus.getClass().getSimpleName() + " - " + ((Char) objectInFocus).getName() + " \n Speed - " +
                        ((Char) objectInFocus).getSpeed() + " \n Wits - " + ((Char) objectInFocus).getWits();
                mainDisplay.setTextToDisplay(textToDisplay);

            }
        }
    }

    @Override
    public void render(Graphics graphics) {
        super.render(graphics);
        for(String key : infoSections.keySet()) {
            infoSections.get(key).render(graphics);
        }
    }
}
