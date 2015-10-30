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
    private AStarPathFinder pathFinder;
    private Path path;
    private int index;
    private int timesWaited;
    private int destX;
    private int destY;

    public MoveEvent(Char character,int destX,int destY) {
        super("Move Event", character);
        this.destY = destY;
        this.destX = destX;
        setSpeed(500);
        index = 0;
        pathFinder = new AStarPathFinder(MainGame.map,100,false);
        resetPath();
        timesWaited = 0;
    }

    private void resetPath() {
        path = pathFinder.findPath(character, character.getX(), character.getY(), destX, destY);
        index = 0;
    }

    @Override
    public void update() {

        Path.Step step = path.getStep(index);
        if(!PathingHelper.isThisTileOccupied(MainGame.getCharacters(),step.getX(),step.getY(),character)) {
            PlaceAction action = new PlaceAction(character, step.getX(), step.getY(),
                    PathingHelper.getDirectionFromStep(step, character.getX(), character.getY()));
            action.update();
            index++;
        } else {
            timesWaited++;
            WaitEvent waitEvent = new WaitEvent(character,this,1);
            character.setCurrentEvent(waitEvent);
            // Plus 4 is the same direction but being still //
            character.setCurrentAnimation(character.getDirection()+4);
            if(timesWaited == 4) {
                timesWaited = 0;
                resetPath();
            }
        }
        // DEBUG, Sometimes getting an uncaught nullpointer for path //
        if (path == null) {
            System.out.println();
        }
        if (index == path.getLength()) {
            character.setCurrentEvent(new IdleEvent(character));
        }
    }
}
