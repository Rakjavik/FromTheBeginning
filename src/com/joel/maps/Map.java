package com.joel.maps;

import com.joel.MainGame;
import com.joel.characters.Char;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

import java.util.List;

/**
 * Created by 430009998 on 10/28/2015.
 */
public class Map implements TileBasedMap {

    private TiledMap tiledMap;
    List<Char> charList;

    public Map(TiledMap tiledMap) {
        this.tiledMap = tiledMap;
    }

    @Override
    public int getWidthInTiles() {
        return tiledMap.getWidth();
    }

    @Override
    public int getHeightInTiles() {
        return tiledMap.getHeight();
    }

    @Override
    public void pathFinderVisited(int i, int i1) {

    }

    @Override
    public boolean blocked(PathFindingContext pathFindingContext, int x, int y) {
        if (tiledMap.getTileProperty(tiledMap.getTileId(x,y,0),"solid","false").equals("true")) {
            return true;
        }
        charList = MainGame.getCharacters();
        for(Char character : charList) {
            if(character.getX() == x && character.getY() == y) {
                return true;
            }
        }
        return false;
    }

    @Override
    public float getCost(PathFindingContext pathFindingContext, int x, int y) {
        String property = tiledMap.getTileProperty(tiledMap.getTileId(x,y,0),"cost","0");
        return Float.parseFloat(property);
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }


}
