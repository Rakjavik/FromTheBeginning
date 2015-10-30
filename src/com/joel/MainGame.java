package com.joel;

import com.joel.characters.Char;
import com.joel.characters.Wizard;
import com.joel.characters.actions.MoveEvent;
import com.joel.characters.actions.WonderingEvent;
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

    public MainGame() {
        super("From the Beginning");
    }

    @Override
    public void keyPressed(int key, char c) {
        if(key == Input.KEY_M) {

        }
    }

    @Override
    public void keyReleased(int key, char c) {
        super.keyReleased(key, c);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        Properties properties = getProperties();
        tilesize = Integer.parseInt(properties.getProperty("tilesize"));
        map = new Map(new TiledMap("maps/test.tmx"));
        characters = new ArrayList<Char>();
        for (int count = 0; count < 15; count++) {
            Wizard wizard = new Wizard();
            wizard.setName(String.valueOf(count));
            wizard.setCurrentEvent(new WonderingEvent(wizard));
            characters.add(wizard);
        }
        Wizard coolerWizard = new Wizard();
        coolerWizard.setX(1);
        coolerWizard.setY(19);
        coolerWizard.setSpeed(100);
        coolerWizard.setName("Blasto");
        coolerWizard.setCurrentEvent(new MoveEvent(coolerWizard, 18, 3));
        Wizard secondTestWiz = new Wizard();
        secondTestWiz.setX(16);
        secondTestWiz.setY(18);
        secondTestWiz.setSpeed(200);
        secondTestWiz.setCurrentEvent(new MoveEvent(secondTestWiz,1,1));
        characters.add(coolerWizard);
        characters.add(secondTestWiz);
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        for (int count = 0; count < characters.size(); count++) {
            characters.get(count).update(i);
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        map.getTiledMap().render(0, 0);
        for (int count = 0; count < characters.size(); count++) {
            characters.get(count).render(graphics);
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
