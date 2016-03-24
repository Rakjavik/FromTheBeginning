package com.joel.characters.actions;

import com.joel.MainGame;
import com.joel.characters.Char;
import com.joel.util.PathingHelper;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by 430009998 on 10/29/2015.
 */
public class MoveEvent extends Event {
    private AStarPathFinder pathFinder;
    private Path path = null;
    private int index;
    private int timesWaited;
    private int timesToWait;
    private int destX;
    private int destY;
    private Char target = null;
    private boolean following = false;
    private int pathingFailures = 0;
    private static final int MAX_NUM_OF_PATHING_FAILURES = 20;

    public MoveEvent(Char character,Char target) {
        super("Following - " + target.getName(),character);
        destX = target.getX();
        destY = target.getY();
        this.target = target;
        following = true;
        init();
    }

    public MoveEvent(Char character,int destX,int destY) {
        super("Moving to " + destX + "-" + destY, character);
        this.destY = destY;
        this.destX = destX;
        init();
    }
    private void init() {
        setSpeed(250 - character.getSpeed()*2);
        index = 0;
        pathFinder = new AStarPathFinder(MainGame.map,100,false);
        timesWaited = 0;
        timesToWait = (int) (12 - (character.getWits()*.1));
        resetPath();
    }
    private void resetPath() {
        if(following) {
            List<Path> possiblePaths = new LinkedList<Path>();
            if (target.getX() > 0) {
                Path pathLeft = pathFinder.findPath(character, character.getX(), character.getY(), target.getX() - 1, target.getY());
                if (pathLeft != null) possiblePaths.add(pathLeft);
            }
            if (target.getX() < MainGame.map.getWidthInTiles()) {
                Path pathRight = pathFinder.findPath(character, character.getX(), character.getY(), target.getX() + 1, target.getY());
                if (pathRight != null) possiblePaths.add(pathRight);
            }
            if (target.getY() > 0) {
                Path pathUp = pathFinder.findPath(character, character.getX(), character.getY(), target.getX(), target.getY() - 1);
                if (pathUp != null) possiblePaths.add(pathUp);
            }
            if (target.getY() < MainGame.map.getHeightInTiles()) {
                Path pathDown = pathFinder.findPath(character, character.getX(), character.getY(), target.getX(), target.getY() + 1);
                if (pathDown != null) possiblePaths.add(pathDown);
            }
            if(pathingFailures > 5) {
                int steps = MainGame.map.getHeightInTiles() * MainGame.map.getWidthInTiles();
                for (Path newPath : possiblePaths) {
                    if (newPath.getLength() < steps) {
                        path = newPath;
                        steps = path.getLength();
                    }
                }
            } else {
                int randomInt = ThreadLocalRandom.current().nextInt(0,4);
                path = possiblePaths.get(randomInt);
            }

        }
        else {
            path = pathFinder.findPath(character, character.getX(), character.getY(), destX, destY);
        }
            index = 0;
        if (path == null) {
            pathingFailures++;
        }
        if (pathingFailures == MAX_NUM_OF_PATHING_FAILURES && !following) {
            character.setCurrentEvent(new IdleEvent(character));
            character.setFreeForAssignment(true);
        }
    }

    @Override
    public void update() {

        //take a cycle to look around
        if( path == null ){
            resetPath();
            if (path == null)
                return;
        }
        // The number of cycles before the path is redone for the moving target //
        else if (12-index == character.getWits() *.1 && following) {
            resetPath();
            if (path == null) {
                return;
            }
        }
        Path.Step step = path.getStep(index);
        if(!PathingHelper.isThisTileOccupied(MainGame.getCharacters(),step.getX(),step.getY(),character)) {
            int direction;
            if (index == 0) {
                direction = character.getDirection();
            } else {
                direction = PathingHelper.getDirectionFromStep(step, character.getX(), character.getY());
            }
            PlaceAction action = new PlaceAction(character, step.getX(), step.getY(),direction);
            action.update();
            index++;
            timesWaited = 0;
            pathingFailures = 0;
        } else {
            timesWaited++;
            WaitEvent waitEvent = new WaitEvent(character,this,ThreadLocalRandom.current().nextInt(1,3));
            character.setCurrentEvent(waitEvent);
            // Plus 4 is the same direction but being still //
            character.setCurrentAnimation(character.getDirection()+4);
            if(timesWaited == timesToWait) {
                timesWaited = 0;
                resetPath();
                return;  //again, lets chill for a cycle as we 'look around'
            }
        }

        if (index == path.getLength() && !following) {
            character.setCurrentEvent(new IdleEvent(character));
        } else if (index == path.getLength() && following) {
            index = 0;
            path = null;
        }
    }
}
