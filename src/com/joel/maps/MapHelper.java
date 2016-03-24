package com.joel.maps;

import com.joel.MainGame;
import org.newdawn.slick.tiled.TiledMap;
import com.joel.item.Item;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by 430009998 on 11/1/2015.
 */
public class MapHelper {

    public static Map populateTrees(Map map) {
        TiledMap tiledMap = map.getTiledMap();
        int percentage = Integer.parseInt(tiledMap.getMapProperty("trees", "0"));
        int numberOfTrees = (int) ((tiledMap.getHeight()*tiledMap.getWidth())*(percentage*.01));
        while(numberOfTrees != 0) {
            int x = ThreadLocalRandom.current().nextInt(0, tiledMap.getWidth()-1);
            int y = ThreadLocalRandom.current().nextInt(0, tiledMap.getHeight()-1);
            if(!map.blocked(null,x,y)) {
                Item tree = MainGame.itemManager.getItemProperties("Tree");
                tree.setX(x);
                tree.setY(y);
                map.getItems().add(tree);
                numberOfTrees--;
            }
        }
        return map;
    }
}
