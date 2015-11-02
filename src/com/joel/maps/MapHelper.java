package com.joel.maps;

import com.joel.MainGame;
import com.joel.item.resources.Tree;
import org.newdawn.slick.tiled.TiledMap;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by 430009998 on 11/1/2015.
 */
public class MapHelper {

    public static void populateTrees(TiledMap map) {
        int percentage = Integer.parseInt(map.getMapProperty("trees","0"));
        int numberOfTrees = (int) ((map.getHeight()*map.getWidth())*(percentage*.01));
        while(numberOfTrees != 0) {
            int x = ThreadLocalRandom.current().nextInt(0, map.getWidth()-1);
            int y = ThreadLocalRandom.current().nextInt(0, map.getHeight()-1);
            if(!MainGame.map.blocked(null,x,y)) {
                Tree tree = new Tree();
                tree.setX(x);
                tree.setY(y);
                MainGame.map.getItems().add(tree);
                numberOfTrees--;
            }
        }
    }
}
