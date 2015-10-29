package com.joel.characters;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created by 430009998 on 10/28/2015.
 */
public class DwarfGuard extends Character {

    public DwarfGuard() {
        super("Dwarf Honor Guard");
        try {
            image = new Image("images/dwarf.png").getScaledCopy(new Float(.3));
        } catch (SlickException e) {
            e.printStackTrace(System.out);
        }
        box = new Rectangle(x,y,image.getWidth(),image.getHeight());
    }
}
