package com.joel.menus;

import com.joel.MainGame;
import com.joel.characters.Char;
import com.joel.item.Item;
import org.newdawn.slick.Color;
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
        xInTiles = widthInTiles - widthInTiles/6;
        yInTiles = 0;
        sizeYInTiles = MainGame.resY - MainGame.resY/6;
        sizeXInTiles = MainGame.resX/6;
        minimumSizeInTiles = 5;
        backGroundColor = Color.blue;
        buttons = new MenuButton[0];
        infoSections = new HashMap<String, InfoSection>();
        InfoSection summarySection = new InfoSection(this);
        infoSections.put("summary", summarySection);
    }

    @Override
    public void update(int delta) {
        super.update(delta);
        if(objectInFocus != MainGame.selected) {
            if(MainGame.selected instanceof Menu) {
                return;
            }
            InfoSection summarySection = infoSections.get("summary");
            objectInFocus = MainGame.selected;
            summarySection.setColor(Color.black);
            summarySection.setFontSize(16);
            if(objectInFocus instanceof Item) {
                summarySection.setTextEnabled(true);
                summarySection.setImageEnabled(true);
                summarySection.setImageKey(((Item) objectInFocus).getImageKey());
                summarySection.setImgPosition(new Point(0, 0));
                summarySection.setTxtPosition(new Point(0, 1));
                String textToDisplay = "Item - " + ((Item) objectInFocus).getName() + " \n Description: \n " + ((Item) objectInFocus).getDescription();
                summarySection.setTextToDisplay(textToDisplay);
            }
            else if(objectInFocus instanceof Char) {
                summarySection.setTextEnabled(true);
                summarySection.setImageEnabled(true);
                summarySection.setImageKey(((Char) objectInFocus).getProtraitKey());
                summarySection.setImgPosition(new Point(0, 0));
                summarySection.setTxtPosition(new Point(0, 1));
                String textToDisplay = objectInFocus.getClass().getSimpleName() + " - " + ((Char) objectInFocus).getName() + " \n Speed - " +
                        ((Char) objectInFocus).getSpeed() + " \n Wits - " + ((Char) objectInFocus).getWits();
                summarySection.setTextToDisplay(textToDisplay);

            }
            else if (objectInFocus instanceof Menu) {
                summarySection.setImageEnabled(false);
                summarySection.setTextEnabled(true);
                summarySection.setTxtPosition(new Point(0, 0));
                summarySection.setTextToDisplay("Oh my, you've selected me, I suppose you could now use the arrow keys to move me");
            }
            else if (objectInFocus == null) {
                summarySection.setImageEnabled(false);
                summarySection.setTextEnabled(false);
            }
        }
    }

    @Override
    public void render(Graphics graphics) {
        super.render(graphics);
        for (String key : infoSections.keySet()) {
            infoSections.get(key).render(graphics);
        }
    }
}
