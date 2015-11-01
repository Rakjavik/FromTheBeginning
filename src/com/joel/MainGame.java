package com.joel;

import com.joel.characters.Char;
import com.joel.characters.actions.IdleEvent;
import com.joel.characters.actions.MoveEvent;
import com.joel.characters.actions.WonderingEvent;
import com.joel.characters.classes.Paladin;
import com.joel.characters.classes.Wizard;
import com.joel.maps.Map;
import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by 430009998 on 10/28/2015.
 */
public class MainGame extends BasicGame {

    private static List<Char> characters;
    public static Map map;
    public static int tilesize;
    private int gameSpeed = 1;
    private int totalDelta = 0;
    private int selected = 0;

    public MainGame() {
        super("From the Beginning");
    }

    @Override
    public void keyPressed(int key, char c) {
        if(key == Input.KEY_W) {
            Wizard wizard = new Wizard();
            wizard.setX(1);
            wizard.setY(1);
            wizard.setCurrentEvent(new WonderingEvent(wizard));
            characters.add(wizard);
        } else if(key == Input.KEY_P) {
            Paladin paladin = new Paladin();
            paladin.setX(1);
            paladin.setY(5);
            paladin.setCurrentEvent(new WonderingEvent(paladin));
            characters.add(paladin);
        }
    }

    @Override
    public void keyReleased(int key, char c) {
        super.keyReleased(key, c);
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        x = x / MainGame.tilesize;
        y = y / MainGame.tilesize;

        for (int count = 0; count < characters.size(); count++) {
            if (x == characters.get(count).getX() && y == characters.get(count).getY()) {
                if (button == Input.MOUSE_LEFT_BUTTON) {
                    selected = count;
                    return;
                } else {
                    characters.get(selected).setCurrentEvent(new MoveEvent(characters.get(selected),characters.get(count)));
                    return;
                }
            }
        }
        if (button == Input.MOUSE_RIGHT_BUTTON)
            characters.get(selected).setCurrentEvent(new MoveEvent(characters.get(selected),x,y));
    }


    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        Properties properties = getProperties();
        tilesize = Integer.parseInt(properties.getProperty("tilesize"));
        map = new Map(new TiledMap("maps/test.tmx"));
        characters = new ArrayList<Char>();
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        totalDelta += i;
        if (totalDelta > gameSpeed) {
            totalDelta = 0;
            for (int count = 0; count < characters.size(); count++) {
                if (characters.get(count).getCurrentEvent() instanceof IdleEvent) {

                }
                characters.get(count).update(i);
            }
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        map.getTiledMap().render(0, 0);
        for (int count = 0; count < characters.size(); count++) {
            characters.get(count).render(graphics);
            if(count == selected) {
                graphics.drawRect(characters.get(count).getX()*tilesize,characters.get(count).getY()*tilesize,tilesize,tilesize);
            }
        }
    }
    public static Properties getProperties() {
        Properties properties = new Properties();
        try {
            properties.load(MainGame.class.getClassLoader().getResourceAsStream("main.properties"));
        } catch(IOException ex) {
            System.err.println("Could not find properties file main.properties");
            System.exit(1);
        }

        return properties;
    }


    public static void main(String[] args) {
        try {
            AppGameContainer container = new AppGameContainer(new MainGame());

            container.setDisplayMode(Integer.parseInt(getProperties().getProperty("resx")),
                    Integer.parseInt(getProperties().getProperty("resy")),false);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace(System.out);
        }
    }

    public static List<Char> getCharacters() {
        return characters;
    }
}
