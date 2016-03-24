package com.joel.util;

import com.joel.MainGame;
import org.apache.commons.io.FileUtils;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by 430009998 on 11/20/2015.
 */
public class ImageManager {
    private static HashMap<String,Image> images = new HashMap<String, Image>();

    public void init() {
        try {
            addImages(new File(System.getProperty("user.dir") + "/images/"));
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    private static void addImages(File directory) throws SlickException {
        Iterator iterator = FileUtils.iterateFiles(directory, null, true);
        while(iterator.hasNext()) {
            File currentFile = (File) iterator.next();
            if(currentFile.isFile()) {
                images.put(currentFile.getName(),new Image(currentFile.getAbsolutePath(), Color.white).
                        getScaledCopy(MainGame.tilesize,MainGame.tilesize));
            }
        }
    }
    public Image getImage(String key) {
        return images.get(key);
    }
}
