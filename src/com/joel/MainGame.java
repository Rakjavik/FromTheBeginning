package com.joel;

import com.joel.characters.Char;
import com.joel.characters.actions.WonderingEvent;
import com.joel.characters.actions.tasks.ChoppingTask;
import com.joel.characters.actions.tasks.HaulingTask;
import com.joel.characters.actions.tasks.Task;
import com.joel.characters.classes.Paladin;
import com.joel.characters.classes.Wizard;
import com.joel.item.Item;
import com.joel.item.misc.stock.StockPile;
import com.joel.maps.Map;
import com.joel.maps.MapHelper;
import com.joel.menus.InfoMenu;
import com.joel.menus.MainMenu;
import com.joel.menus.Menu;
import com.joel.menus.MenuButton;
import com.joel.util.ImageManager;
import com.joel.util.ItemManager;
import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * Created by 430009998 on 10/28/2015.
 */
public class MainGame extends BasicGame {

    private static final List<Char> characters = new LinkedList<Char>();
    private static final List<Task> tasks = new LinkedList<Task>();
    private static Menu[] menus;
    public static int resX;
    public static int resY;
    public static Map map;
    public static int tilesize;
    public static int viewX = 0;
    public static int viewY = 0;
    private int gameSpeed = 1;
    private int totalDelta = 0;
    public static Object selected;
    public static ItemManager itemManager = new ItemManager();
    public static ImageManager imageManager = new ImageManager();

    public MainGame() {
        super("From the Beginning");
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        Properties properties = getProperties();
        tilesize = Integer.parseInt(properties.getProperty("tilesize"));
        map = new Map(new TiledMap("maps/test.tmx"));
        imageManager.init();
        itemManager.init();
        map = MapHelper.populateTrees(map);
        menus = new Menu[2];
        menus[0] = new MainMenu();
        menus[1] = new InfoMenu();
        loadTesting();
    }

    private void loadTesting() {
        StockPile stockPile = new StockPile(StockPile.STOCK_TYPE_CRAFT_MATERIAL);
        stockPile.setX(1);
        stockPile.setY(1);
        map.getStockPiles().add(stockPile);
        Item item = itemManager.getItemProperties("Tree");
        item.setX(4);
        item.setY(3);
        map.getItems().add(item);

    }

    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_W) {
            Wizard wizard = new Wizard();
            wizard.setX(1);
            wizard.setY(1);
            wizard.setCurrentEvent(new WonderingEvent(wizard));
            characters.add(wizard);
        } else if (key == Input.KEY_P) {
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
            if (viewY < map.getMaxYInPixels()) {
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
    public void mouseClicked(int button, int x, int y, int clickCount) {
        x = x / MainGame.tilesize + viewX / tilesize;
        y = y / MainGame.tilesize + viewY / tilesize;

        Object click = whatDidIJustClickOn(x, y);

        if (click instanceof Char) {
            if (button == Input.MOUSE_LEFT_BUTTON) {
                selected = (Char) click;
                return;
            }
        }
        if (click instanceof Item) {
            if (button == Input.MOUSE_RIGHT_BUTTON) {
                if (((Item) click).isChoppable()) {
                    ((Item) click).setSelected(true);
                    ChoppingTask choppingTask = new ChoppingTask((Item) click);
                    tasks.add(choppingTask);
                }
            } else if (button == Input.MOUSE_LEFT_BUTTON) {
                selected = click;
            }
        }
        if (click instanceof StockPile) {
            if (button == Input.MOUSE_LEFT_BUTTON) {
                selected = click;
            }
        }
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
                for (Task task : tasks) {
                    task.update(i);
                }
                for (int count = 0; count < tasks.size(); count++) {
                    if (tasks.get(count).isTaskComplete()) {
                        tasks.get(count).getAssignedCharacter().setFreeForAssignment(true);
                        tasks.remove(tasks.get(count));
                    }
                }
                processHauling();
                for (Menu menu : menus) {
                    menu.update(i);
                }
            }
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        map.getTiledMap().render(-viewX, -viewY);
        for (Item item : map.getItems()) {
            item.render(graphics);
        }
        for (Item item : map.getStockPiles()) {
            item.render(graphics);
        }
        for (int count = 0; count < characters.size(); count++) {
            characters.get(count).render(graphics);
        }
        if (selected != null) {
            if (selected instanceof Char) {
                graphics.drawRect(((Char) selected).getX() * tilesize - MainGame.viewX,
                        ((Char) selected).getY() * tilesize - MainGame.viewY, tilesize, tilesize);

            } else if (selected instanceof Item) {
                graphics.drawRect(((Item) selected).getX() * tilesize - MainGame.viewX,
                        ((Item) selected).getY() * tilesize - MainGame.viewY, tilesize, tilesize);
            } else if (selected instanceof StockPile) {
                graphics.drawRect(((StockPile) selected).getX() * tilesize - MainGame.viewX,
                        ((StockPile) selected).getY() * tilesize - MainGame.viewY, tilesize, tilesize);
            }
        }
        for (Menu menu : menus) {
            menu.render(graphics);
        }
    }

    public static Properties getProperties() {
        Properties properties = new Properties();
        try {
            properties.load(MainGame.class.getClassLoader().getResourceAsStream("main.properties"));
        } catch (IOException ex) {
            System.err.println("Could not find properties file main.properties");
            System.exit(1);
        }

        return properties;
    }


    public static void main(String[] args) {
        try {
            resX = Integer.parseInt(getProperties().getProperty("resx"));
            resY = Integer.parseInt(getProperties().getProperty("resy"));
            AppGameContainer container = new AppGameContainer(new MainGame());
            container.setDisplayMode(resX, resY, false);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace(System.out);
        }
    }

    public static List<Char> getCharacters() {
        return characters;
    }

    private void processHauling() {
        for (int count = 0; count < map.getItems().size(); count++) {
            if (map.getItems().get(count).isStockable()) {
                if (!map.getItems().get(count).isStored() && map.getItems().get(count).isAvailable()) {
                    StockPile destinationPile = getOpenStockPile((map.getItems().get(count)).getStockType());
                    if (destinationPile != null) {
                        map.getItems().get(count).setAvailable(false);
                        Task haulingTask = new HaulingTask(map.getItems().get(count), destinationPile);
                        tasks.add(haulingTask);
                    }
                }
            }
        }
    }

    private StockPile getOpenStockPile(int stockType) {
        for (StockPile stockPile : map.getStockPiles()) {
            if (!stockPile.isFull() && stockPile.getStockType() == stockType) {
                return stockPile;
            }
        }
        return null;
    }

    private Object whatDidIJustClickOn(int xTile, int yTile) {
        for (Char character : characters) {
            if (character.getX() == xTile && character.getY() == yTile) {
                return character;
            }
        }
        for (Item item : map.getItems()) {
            if (item.getX() == xTile && item.getY() == yTile) {
                return item;
            }
        }
        for (Item item : map.getStockPiles()) {
            if (item.getX() == xTile && item.getY() == yTile) {
                return item;
            }
        }
        for (Menu menu : menus) {
            if (menu.isEnabled()) {
                for (MenuButton button : menu.getButtons()) {
                    if (xTile == button.getX() && yTile == button.getY()) {
                        return button;
                    }
                }
            }
        }
        return null;
    }
}
