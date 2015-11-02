package com.joel.maps;

import com.joel.MainGame;
import com.joel.characters.Char;
import com.joel.item.Item;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 430009998 on 10/28/2015.
 */
public class Map implements TileBasedMap {

    private TiledMap tiledMap;
    private List<Char> charList;
    private int maxXInPixels;
    private int maxYInPixels;
    private List<Item> items;

    public Map(TiledMap tiledMap) {
        this.tiledMap = tiledMap;
        maxYInPixels = getHeightInTiles()*MainGame.tilesize - MainGame.resY;
        maxXInPixels = getWidthInTiles()*MainGame.tilesize - MainGame.resX;
        items = new LinkedList<Item>();

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

    public int getMaxXInPixels() {
        return maxXInPixels;
    }

    public int getMaxYInPixels() {
        return maxYInPixels;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
