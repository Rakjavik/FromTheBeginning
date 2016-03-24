package com.joel.util;

import com.google.gson.Gson;
import com.joel.item.Item;

import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by 430009998 on 11/19/2015.
 */
public class ItemManager {
    private static HashMap<String, Item> items = new HashMap<String, Item>();


    public void init() {
        Item[] itemsToAdd = new Gson().fromJson(new InputStreamReader(ItemManager.class.getClass().getResourceAsStream("/items/items.json")), Item[].class);
        for (Item item : itemsToAdd) {
            items.put(item.getName(), item);
        }
    }

    public Item getItemProperties(String key) {
        Item item = items.get(key);
        return (Item) item.clone();
    }
}
