package com.joel.item.misc.stock;

import com.joel.MainGame;
import com.joel.item.Item;

/**
 * Created by 430009998 on 11/2/2015.
 */
public class StockPile extends Item {

    public static final int STOCK_TYPE_CRAFT_MATERIAL = 0;

    protected boolean isFull = false;
    protected int stockType;
    protected static String[] titles;

    static {
        titles = new String[1];
        titles[STOCK_TYPE_CRAFT_MATERIAL] = "Crafting Material";
    }

    public StockPile(int stockPileType) {
        super("Stock Pile - " + titles[stockPileType]);
        render = true;
        image = MainGame.cachedImages[0];
        this.stockType = stockPileType;
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean isFull) {
        this.isFull = isFull;
    }

    public int getStockType() {
        return stockType;
    }
}
