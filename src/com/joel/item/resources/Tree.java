package com.joel.item.resources;

import com.joel.MainGame;
import com.joel.item.Item;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by 430009998 on 11/1/2015.
 */
public class Tree extends Item implements ResourceInterface{
    public Tree() {
        super("Tree");
        render = true;
        try {
            image = new Image("images/items/tree.png", Color.white).getScaledCopy(MainGame.tilesize,MainGame.tilesize);
        } catch (SlickException e) {
            e.printStackTrace(System.out);
        }
    }

    @Override
    public boolean isChoppable() {
        return true;
    }
}
