package com.joel;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 * Created by 430009998 on 10/28/2015.
 */
public class MapEditor {


    public static void main(String[] args) {
        try {
            AppGameContainer container = new AppGameContainer(new MapEditorGame());
            container.setDisplayMode(1280,720,false);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace(System.out);
        }
    }
}
