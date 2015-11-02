package com.joel;

import com.joel.characters.Char;
import com.joel.characters.actions.MoveEvent;
import com.joel.characters.actions.WonderingEvent;
import com.joel.characters.actions.tasks.ChoppingTask;
import com.joel.characters.actions.tasks.Task;
import com.joel.characters.classes.Paladin;
import com.joel.characters.classes.Wizard;
import com.joel.item.Item;
import com.joel.item.resources.ResourceInterface;
import com.joel.maps.Map;
import com.joel.maps.MapHelper;
import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * Created by 430009998 on 10/28/2015.
 */
public class MainGame extends BasicGame {

    private static List<Char> characters;
    private static List<Task> tasks = new LinkedList<Task>();
    public static int resX;
    public static int resY;
    public static Map map;
    public static int tilesize;
    public static int viewX = 0;
    public static int viewY = 0;
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
        } else if (key == Input.KEY_SPACE) {
            if (gameSpeed == 0) {
                gameSpeed = 1;
            } else {
                gameSpeed = 0;
            }
        } else if (key == Input.KEY_UP) {
            if (viewY > 0) {
                viewY -= tilesize;
            }
        } else if (key == Input.KEY_DOWN) {
            if(viewY < map.getMaxYInPixels()) {
                viewY += tilesize;
            }
        } else if (key == Input.KEY_LEFT) {
            if (viewX > 0) {
                viewX -= tilesize;
            }
        } else if (key == Input.KEY_RIGHT) {
            if (viewX < map.getMaxXInPixels()) {
                viewX += tilesize;
            }
        } else if (key == Input.KEY_ESCAPE) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(int key, char c) {
        super.keyReleased(key, c);
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        x = x / MainGame.tilesize + viewX/tilesize;
        y = y / MainGame.tilesize + viewY/tilesize;

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
        if (button == Input.MOUSE_RIGHT_BUTTON) {
            for(Item item : map.getItems()) {
                if (x == item.getX() && y == item.getY()) {
                    try {
                        if (((ResourceInterface) item).isChoppable()) {
                            item.setSelected(true);
                            ChoppingTask choppingTask = new ChoppingTask(item);
                            tasks.add(choppingTask);
                        }
                    } catch(ClassCastException e) {
                        System.out.println("Item not choppable");
                    }
                }
            }
        }
    }


    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        Properties properties = getProperties();
        tilesize = Integer.parseInt(properties.getProperty("tilesize"));
        map = new Map(new TiledMap("maps/test.tmx"));
        characters = new ArrayList<Char>();
        MapHelper.populateTrees(map.getTiledMap());
        loadTesting();
    }

    private void loadTesting() {


    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        if (gameSpeed != 0) {
            totalDelta += i;
            if (totalDelta > gameSpeed) {
                totalDelta = 0;
                for (int count = 0; count < characters.size(); count++) {
                    characters.get(count).update(i);
                }
                for(Task task : tasks) {
                    task.update(i);
                }
                for(int count = 0; count < tasks.size(); count++) {
                    if (tasks.get(count).isTaskComplete()) {
                        tasks.get(count).getAssignedCharacter().setFreeForAssignment(true);
                        tasks.remove(tasks.get(count));
                    }
                }
            }
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        map.getTiledMap().render(-viewX, -viewY);
        for(Item item : map.getItems()) {
            item.render(graphics);
        }
        for (int count = 0; count < characters.size(); count++) {
            characters.get(count).render(graphics);
            if(count == selected) {
                graphics.drawRect(characters.get(count).getX()*tilesize-MainGame.viewX,
                        characters.get(count).getY()*tilesize-MainGame.viewY,tilesize,tilesize);
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
            resX = Integer.parseInt(getProperties().getProperty("resx"));
            resY = Integer.parseInt(getProperties().getProperty("resy"));
            container.setDisplayMode(resX,resY,false);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace(System.out);
        }
    }

    public static List<Char> getCharacters() {
        return characters;
    }

    public static List<Task> getTasks() {
        return tasks;
    }
}
