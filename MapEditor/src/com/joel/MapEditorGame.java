package com.joel;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.TextField;


/**
 * Created by 430009998 on 10/28/2015.
 */
public class MapEditorGame extends BasicGame {

    private Font font;
    private TextField textField;
    Image mapImage;
    boolean displayGrid;
    private Rectangle imageRect;


    public MapEditorGame() {
        super("Map Editor");
    }


    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_ESCAPE) {
            System.exit(0);
        } else if (key == Input.KEY_ENTER) {
            String location = textField.getText();
            try {
                mapImage = new Image("/images/" + location) ;
            } catch (SlickException e) {
                e.printStackTrace();
            }
            imageRect = new Rectangle(321,51,mapImage.getWidth(),mapImage.getHeight());
        } else if (c == 'g') {
            if(displayGrid) displayGrid = false;
            else displayGrid = true;
        } else {
            System.out.println(key + " " + c);
        }
    }

    @Override
    public void mousePressed(int button, int x, int y) {
        if (imageRect.contains(x,y)) {
            System.out.println("Done intersected");
        }
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        java.awt.Font awtFont = new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 24);
        font = new TrueTypeFont(awtFont, false);
        textField = new TextField(gameContainer,font,970,100,300,25);
        textField.setText("Image");
        mapImage = new Image("/images/bgp.png");
        imageRect = new Rectangle(321,51,mapImage.getWidth(),mapImage.getHeight());
        displayGrid = true;
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        graphics.setColor(Color.blue);
        graphics.drawRect(0, 0, 1599, 899);
        graphics.drawRect(320,50,640,450);
        graphics.setColor(Color.darkGray);
        textField.render(gameContainer,graphics);
        if (mapImage != null) {
            graphics.drawImage(mapImage,321,51);
            font.drawString(460,20,"Map Width - " + mapImage.getWidth()/MainGame.tilesize + " Map Height - " +
            mapImage.getHeight()/MainGame.tilesize);
            if (displayGrid) {
                graphics.setColor(Color.white);
                int x = 320;
                int y = 51;
                while(x < 960-MainGame.tilesize) {
                    x += MainGame.tilesize;
                    graphics.drawLine(x,y,x,499);
                }
                x = 321;
                while(y < 500-MainGame.tilesize) {
                    y += MainGame.tilesize;
                    graphics.drawLine(x,y,959,y);
                }
            }
        }
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        System.out.println(x + "-" + y);
    }


}
