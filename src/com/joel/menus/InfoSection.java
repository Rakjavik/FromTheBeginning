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
    private InfoMenu parent;
    private Font font;
    private boolean enabled = false;

    public InfoSection(InfoMenu parent) {
        this.parent = parent;
        java.awt.Font font = new java.awt.Font("Verdana", java.awt.Font.BOLD, 16);
        TrueTypeFont ttf = new TrueTypeFont(font, true);
        this.font = ttf;
    }

    @Override
    public void render(Graphics graphics) {
        if(enabled) {
            graphics.setColor(color);
            graphics.setFont(font);
            drawStrings(graphics);
            Image image = MainGame.imageManager.getImage(imageKey);
            int centerOffset = (parent.getSizeX() - image.getWidth())/2;
            graphics.drawImage(MainGame.imageManager.getImage(imageKey), imgPosition.x * MainGame.tilesize + centerOffset, imgPosition.y * MainGame.tilesize);
        }
    }

    private void drawStrings(Graphics graphics) {
        int txtWidth = font.getWidth(textToDisplay);
        if(txtWidth < parent.getSizeX()) {
            graphics.drawString(textToDisplay,txtPosition.x*MainGame.tilesize,txtPosition.y*MainGame.tilesize);
            return;
        }
        int indexOfStrings = 0;
        StringBuilder builder = new StringBuilder();
        String[] strings = textToDisplay.split(" ");
        boolean moreLines = true;
        int count = 0;
        while(moreLines) {
            for(int stringI = indexOfStrings; stringI < strings.length; stringI++) {
                if(parent.getSizeX() < font.getWidth(strings[stringI] + " ")+35) {
                    System.out.println("ERROR InfoSection line too long - " + strings[stringI]);
                    System.exit(5);
                }
                // 35 is the tweaked number to make it look better when centering //
                if(font.getWidth(builder.toString()+strings[stringI] + " ")+35 < parent.getSizeX()
                        && !strings[stringI].contains("\n")) {
                    builder = builder.append(strings[stringI] + " ");
                    indexOfStrings++;
                } else {
                    int centerOffset = (parent.getSizeX()-font.getWidth(builder.toString()))/2;
                    graphics.drawString(builder.toString(),txtPosition.x*MainGame.tilesize+centerOffset,(txtPosition.y+count)*MainGame.tilesize);
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
                int centerOffset = (parent.getSizeX()-font.getWidth(builder.toString()))/2;
                graphics.drawString(builder.toString(), txtPosition.x * MainGame.tilesize+centerOffset, (txtPosition.y + count) * MainGame.tilesize);
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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
}
