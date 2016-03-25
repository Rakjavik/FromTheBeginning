package com.joel.util;

import com.joel.characters.Char;
import com.joel.characters.actions.tasks.Task;
import com.joel.item.Item;
import com.joel.maps.Map;
import com.joel.menus.Menu;
import com.joel.menus.MenuButton;

import java.util.List;

/**
 * Created on 3/25/2016 by Joel Blanchette.
 * Shared Application Developer
 */
public class Util {
    public static Object whatDidIJustClickOn(int xTile, int yTile,List<Char> characters,Map map,Menu[] menus) {
        for (Menu menu : menus) {
            for (MenuButton button : menu.getButtons()) {
                if (xTile == button.getX() && yTile == button.getY()) {
                    return button;
                }
            }
            if (xTile >= menu.getxInTiles() && xTile <= menu.getxInTiles() + menu.getSizeXInTiles()) {
                if (yTile >= menu.getyInTiles() && yTile <= menu.getyInTiles() + menu.getSizeYInTiles()) {
                    return menu;
                }
            }
        }
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
        return null;
    }

    public static Task getTaskByTarget(Object target, List<Task> tasks) {
        for (Task task : tasks) {
            if (task.getTarget().equals(target)) {
                return task;
            }
        }
        return null;
    }

}
