package com.joel.item;

import com.joel.MainGame;
import com.joel.item.misc.stock.StockPile;

import java.util.LinkedList;
import java.util.List;

/**
 * Created on 3/23/2016 by Joel Blanchette.
 * Shared Application Developer
 */
public class ItemHelper {
    public static Item[] getItemsInTile(List<Item> allItems,int x,int y,Item disclude) {
        List<Item> items = new LinkedList<Item>();
        for(Item item : allItems) {
            if(item.getX() == x && item.getY() == y) {
                if(disclude == null || disclude != item) {
                    items.add(item);
                }
            }
        }
        Item[] returnArray = new Item[items.size()];
        items.toArray(returnArray);
        return returnArray;
    }
    public static boolean stockItem(Item itemToAdd,StockPile stockPile,List<Item> allItems) {
        if(stockPile.isFull()) return false;

        Item[] items = getItemsInTile(allItems,stockPile.getX(),stockPile.getY(),itemToAdd);
        for(int count = 0; count < items.length; count++) {
            if(items[count].getName().equals(itemToAdd.getName())) {
                if(items[count].getStackSize() < items[count].getStackSizeMax()) {
                    items[count].setStackSize(items[count].getStackSize()+1);
                    allItems.remove(itemToAdd);
                    return true;
                }
            }
        }
        return true;
    }
}
