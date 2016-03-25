package com.joel.characters.actions.tasks;

import com.joel.MainGame;
import com.joel.characters.actions.MoveEvent;
import com.joel.item.Item;
import com.joel.item.ItemHelper;
import com.joel.item.misc.stock.StockPile;

/**
 * Created by 430009998 on 11/2/2015.
 */
public class HaulingTask extends Task {
    private Item itemToBeHauled;
    public HaulingTask(Item item,StockPile stockPile) {
        super("Hauling - " + item.getName());
        this.itemToBeHauled = item;
        this.target = stockPile;
    }

    @Override
    public void update(int delta) {
        super.update(delta);
        if (!waitingForEvent && assignedCharacter != null) {
            StockPile targetStockPile = (StockPile) target;
            if(step == 0) {
                // Make sure the target location isn't identical to the source //
                if (assignedCharacter.getX() != itemToBeHauled.getX() && assignedCharacter.getY() != itemToBeHauled.getY()) {
                    MoveEvent moveEvent = new MoveEvent(assignedCharacter, itemToBeHauled.getX(), itemToBeHauled.getY());
                    assignedCharacter.setCurrentEvent(moveEvent);
                    waitingForEvent = true;
                }
            } else if (step == 1) {
                assignedCharacter.getItems().add(itemToBeHauled);
                itemToBeHauled.setStored(true);
                itemToBeHauled.setRender(false);
                MoveEvent moveEvent = new MoveEvent(assignedCharacter, targetStockPile.getX(), targetStockPile.getY());
                assignedCharacter.setCurrentEvent(moveEvent);
                waitingForEvent = true;
            } else if (step == 2) {
                itemToBeHauled.setX(targetStockPile.getX());
                itemToBeHauled.setY(targetStockPile.getY());
                itemToBeHauled.setRender(true);
                itemToBeHauled.setAvailable(true);
                boolean success = ItemHelper.stockItem(itemToBeHauled, targetStockPile, MainGame.map.getItems());
                if(success) {
                    taskComplete = true;
                    assignedCharacter.getItems().remove(itemToBeHauled);
                }
            }
        }
    }

    @Override
    public void cleanup() {
        super.cleanup();
        itemToBeHauled.setAvailable(true);
        itemToBeHauled.setRender(true);
        if (assignedCharacter != null) {
            itemToBeHauled.setX(assignedCharacter.getX());
            itemToBeHauled.setY(assignedCharacter.getY());
        }
    }
}
