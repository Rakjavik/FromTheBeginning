package com.joel.characters.actions.tasks;

import com.joel.characters.actions.MoveEvent;
import com.joel.item.Item;
import com.joel.item.misc.stock.StockPile;

/**
 * Created by 430009998 on 11/2/2015.
 */
public class HaulingTask extends Task {
    private Item itemToBeHauled;
    private StockPile destinationStockPile;
    public HaulingTask(Item item,StockPile stockPile) {
        super("Hauling - " + item.getName());
        this.itemToBeHauled = item;
        this.destinationStockPile = stockPile;
    }

    @Override
    public void update(int delta) {
        super.update(delta);
        if (!waitingForEvent && assignedCharacter != null) {
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
                //TODO add to char inventory
                MoveEvent moveEvent = new MoveEvent(assignedCharacter,destinationStockPile.getX(),destinationStockPile.getY());
                assignedCharacter.setCurrentEvent(moveEvent);
                waitingForEvent = true;
            } else if (step == 2) {
                itemToBeHauled.setX(destinationStockPile.getX());
                itemToBeHauled.setY(destinationStockPile.getY());
                itemToBeHauled.setRender(true);
                itemToBeHauled.setAvailable(true);
                destinationStockPile.setFull(true);
                taskComplete = true;
                assignedCharacter.getItems().remove(itemToBeHauled);
            }
        }
    }
}
