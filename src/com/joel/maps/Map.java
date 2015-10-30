package com.joel.maps;

import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

/**
 * Created by 430009998 on 10/28/2015.
 */
public class Map implements TileBasedMap {

    private TiledMap tiledMap;

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
    public boolean blocked(PathFindingContext pathFindingContext, int i, int i1) {
        if (tiledMap.getTileProperty(tiledMap.getTileId(i,i1,0),"solid","false").equals("true")) {
            return true;
        }
        return false;
    }

    @Override
    public float getCost(PathFindingContext pathFindingContext, int i, int i1) {
        return 0;
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }


}
