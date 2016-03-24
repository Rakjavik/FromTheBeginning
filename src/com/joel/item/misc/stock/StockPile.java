package com.joel.item.misc.stock;

import com.joel.item.Item;

/**
 * Created by 430009998 on 11/2/2015.
 */
public class StockPile extends Item {

    public static final int STOCK_TYPE_CRAFT_MATERIAL = 0;

    private int stockType;
    private static String[] titles;
    private Item stockedItem = null;

    static {
        titles = new String[1];
        titles[STOCK_TYPE_CRAFT_MATERIAL] = "Crafting Material";
    }

    public StockPile(int stockPileType) {
        super("Stock Pile - " + titles[stockPileType]);
        render = true;
        this.stockType = stockPileType;
        imageKey = "stock.png";
    }

    public boolean isFull() {
        if(stockedItem == null) return false;
        if(stockedItem.getStackSize() < stockedItem.getStackSizeMax()) {
            return false;
        }
        return true;
    }

    public int getStockType() {
        return stockType;
    }

}
