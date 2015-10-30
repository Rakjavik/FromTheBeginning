package com.joel.characters.actions;

import com.joel.MainGame;
import com.joel.characters.Char;
import com.joel.util.PathingHelper;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;

/**
 * Created by 430009998 on 10/29/2015.
 */
public class MoveEvent extends Event {
    private static AStarPathFinder pathFinder;
    private Path path;
    private int index;

    public MoveEvent(Char character,int destX,int destY) {
        super("Move Event", character);
        setSpeed(500);
        index = 0;
        pathFinder = new AStarPathFinder(MainGame.map,50,false);
        path = pathFinder.findPath(character, character.getX(), character.getY(), destX, destY);

    }



    @Override
    public void update() {
        Path.Step step = path.getStep(index);
        index++;
        if(!PathingHelper.isThisTileOccupied(MainGame.getCharacters(),step.getX(),step.getY(),character)) {
            PlaceAction action = new PlaceAction(character, step.getX(), step.getY(),
                    PathingHelper.getDirectionFromStep(step, character.getX(), character.getY()));
            action.update();
        } else {
            index--;
            WaitEvent waitEvent = new WaitEvent(character,this,2);
            character.setCurrentEvent(waitEvent);
            // Plus 4 is the same direction but being still //
            character.setCurrentAnimation(character.getDirection()+4);
        }

        if (index == path.getLength()) {
            character.setCurrentEvent(new IdleEvent(character));
        }
    }
}
