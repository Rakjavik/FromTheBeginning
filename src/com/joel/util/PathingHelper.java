package com.joel.util;

import com.joel.characters.Char;
import com.joel.characters.actions.Event;
import org.newdawn.slick.util.pathfinding.Path;

import java.util.List;

/**
 * Created by 430009998 on 10/29/2015.
 */
public class PathingHelper {
    public static int getDirectionFromStep(Path.Step step,int charX,int charY) {
        if(step.getX() > charX) {
            return Event.DIRECTION_RIGHT;
        } else if (step.getX() < charX) {
            return Event.DIRECTION_LEFT;
        } else if (step.getY() > charY) {
            return Event.DIRECTION_DOWN;
        } else if (step.getY() < charY) {
            return Event.DIRECTION_UP;
        } else {
            return 0;
        }
    }
    public static boolean isThisTileOccupied(List<Char> chars,int xOfTile, int yOfTile,Char disclude) {
        for(Char character : chars) {
            if(character.getX() == xOfTile && character.getY() == yOfTile && !disclude.equals(character)) {
                return true;
            }
        }
        return false;
    }
}
