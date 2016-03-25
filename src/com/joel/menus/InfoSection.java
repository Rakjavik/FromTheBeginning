package com.joel.menus;

import com.joel.MainGame;
import com.joel.Renderable;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.awt.*;

/**
 * Created on 3/23/2016 by Joel Blanchette.
 * Shared Application Developer
 */
public class InfoSection implements Renderable {
    private String textToDisplay;
    private Point imgPosition;
    private Point txtPosition;
    private Color color;
    private String imageKey;
    private Menu parent;
    private Font font;
    private boolean textEnabled = false;
    private boolean imageEnabled = false;

    public InfoSection(Menu parent) {
        this.parent = parent;
        java.awt.Font font = new java.awt.Font("Verdana", java.awt.Font.BOLD, 16);
        TrueTypeFont ttf = new TrueTypeFont(font, true);
        this.font = ttf;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(color);
        graphics.setFont(font);
        if(textEnabled)
            drawStrings(graphics);
        if(imageEnabled) {
            Image image = MainGame.imageManager.getImage(imageKey);
            int centerOffset = (parent.getSizeXInTiles() * MainGame.tilesize - image.getWidth()) / 2;
            graphics.drawImage(MainGame.imageManager.getImage(imageKey), (parent.getxInTiles()+imgPosition.x) * MainGame.tilesize + centerOffset,
                    (parent.getyInTiles()+imgPosition.y) * MainGame.tilesize);
        }

    }

    private void drawStrings(Graphics graphics) {
        int txtWidth = font.getWidth(textToDisplay);
        if(txtWidth < parent.getSizeXInTiles()) {
            graphics.drawString(textToDisplay,(parent.getxInTiles()+txtPosition.x)*MainGame.tilesize,(parent.getyInTiles()+txtPosition.y)*MainGame.tilesize);
            return;
        }
        int indexOfStrings = 0;
        StringBuilder builder = new StringBuilder();
        String[] strings = textToDisplay.split(" ");
        boolean moreLines = true;
        int count = 0;
        while(moreLines) {
            for(int stringI = indexOfStrings; stringI < strings.length; stringI++) {
                if(parent.getSizeXInTiles()*MainGame.tilesize < font.getWidth(strings[stringI] + " ")+35) {
                    System.out.println("ERROR InfoSection line too long - " + strings[stringI] + " Size of menu - " + parent.getSizeXInTiles());
                    System.exit(5);
                }
                // 35 is the tweaked number to make it look better when centering //
                if(font.getWidth(builder.toString()+strings[stringI] + " ")+35 < parent.getSizeXInTiles()*32
                        && !strings[stringI].contains("\n")) {
                    builder = builder.append(strings[stringI] + " ");
                    indexOfStrings++;
                } else {
                    int centerOffset = (parent.getSizeXInTiles()*MainGame.tilesize-font.getWidth(builder.toString()))/2;
                    graphics.drawString(builder.toString(),(parent.getxInTiles()+txtPosition.x)*MainGame.tilesize+centerOffset,
                            (parent.getyInTiles()+txtPosition.y+count)*MainGame.tilesize);
                    builder = new StringBuilder();
                    if(strings[stringI].contains("\n")) {
                        indexOfStrings++;
                    }
                    count++;
                    break;
                }
            }
            if(indexOfStrings == strings.length) {
                moreLines = false;
                int centerOffset = (parent.getSizeXInTiles()*MainGame.tilesize-font.getWidth(builder.toString()))/2;
                graphics.drawString(builder.toString(), (parent.getxInTiles()+txtPosition.x) * MainGame.tilesize+centerOffset,
                        (parent.getyInTiles() + txtPosition.y + count) * MainGame.tilesize);
            }
        }
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

    public void setImgPosition(Point imgPosition) {
        this.imgPosition = imgPosition;
    }

    public void setTxtPosition(Point txtPosition) {
        this.txtPosition = txtPosition;
    }

    public Font getFont() {
        return font;
    }

    public void setFontSize(int size) {
        java.awt.Font font = new java.awt.Font("Verdana", java.awt.Font.PLAIN, size);
        TrueTypeFont ttf = new TrueTypeFont(font, true);
        this.font = ttf;
    }

    public void setTextEnabled(boolean textEnabled) {
        this.textEnabled = textEnabled;
    }

    public void setImageEnabled(boolean imageEnabled) {
        this.imageEnabled = imageEnabled;
    }
}
