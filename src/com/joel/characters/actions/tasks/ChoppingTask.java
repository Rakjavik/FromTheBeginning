package com.joel.characters.actions.tasks;

import com.joel.MainGame;
import com.joel.characters.actions.MoveEvent;
import com.joel.characters.actions.WaitEvent;
import com.joel.item.Item;

/**
 * Created by 430009998 on 11/1/2015.
 */
public class ChoppingTask extends Task {
    private Item target;
    public ChoppingTask(Item target) {
        super("Chopping");
        this.target = target;
        priority = 500;
    }
    @Override
    public void update(int delta) {
        super.update(delta);
        if (!waitingForEvent && assignedCharacter != null) {
            if (step == 0) {
                assignedCharacter.setCurrentEvent(new MoveEvent(assignedCharacter,target.getX(),target.getY()));
                waitingForEvent = true;
            } else if (step == 1) {
                //TODO assigned cycle time by character skill
                assignedCharacter.setCurrentEvent(new WaitEvent(assignedCharacter,null,10));
                waitingForEvent = true;
            } else if (step == 2) {
                MainGame.map.getItems().remove(target);
                Item wood = new Item("Wood");
                wood.setX(target.getX());
                wood.setY(target.getY());
                MainGame.map.getItems().add(wood);
                taskComplete = true;
            }
        }
    }
}
