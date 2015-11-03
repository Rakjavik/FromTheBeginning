package com.joel.item.resources;

import com.joel.MainGame;
import com.joel.item.Item;
import com.joel.item.misc.stock.StockPile;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by 430009998 on 11/1/2015.
 */
public class Wood extends Item implements StockableInterface {
    public Wood() {
        super("Wood");
        render = true;
        try {
            image = new Image("images/items/log.png", Color.white).getScaledCopy(MainGame.tilesize,MainGame.tilesize);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getStockType() {
        return StockPile.STOCK_TYPE_CRAFT_MATERIAL;
    }
}
