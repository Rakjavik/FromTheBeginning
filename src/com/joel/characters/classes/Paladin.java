package com.joel.characters.classes;

import com.joel.characters.Char;
import org.newdawn.slick.*;

/**
 * Created by 430009998 on 10/30/2015.
 */
public class Paladin extends Char {

    public Paladin() {
        super("Paladin");
        try {
            spriteSheet = new SpriteSheet("images/pal1.png",32,32,Color.white);
        } catch (SlickException e) {
            e.printStackTrace(System.out);
        }
        walkAnimationSpeed = 200;
        speed = 100;
        wits = 100;
        Image[] images = new Image[2];
        images[0] = spriteSheet.getSprite(0,0);
        images[1] = spriteSheet.getSprite(1,0);
        animations[Char.WALK_UP] = new Animation(images,walkAnimationSpeed);
        images[0] = spriteSheet.getSprite(2,0);
        images[1] = spriteSheet.getSprite(3,0);
        animations[Char.WALK_DOWN] = new Animation(images,walkAnimationSpeed);
        images[0] = spriteSheet.getSprite(0,1);
        images[1] = spriteSheet.getSprite(1,1);
        animations[Char.WALK_LEFT] = new Animation(images,walkAnimationSpeed);
        images[0] = spriteSheet.getSprite(2,1);
        images[1] = spriteSheet.getSprite(3,1);
        animations[Char.WALK_RIGHT] = new Animation(images,walkAnimationSpeed);
        images = new Image[1];
        images[0] = spriteSheet.getSprite(0,0);
        animations[Char.STAND_UP] = new Animation(images,walkAnimationSpeed);
        images[0] = spriteSheet.getSprite(2,0);
        animations[Char.STAND_DOWN] = new Animation(images,walkAnimationSpeed);
        images[0] = spriteSheet.getSprite(0,1);
        animations[Char.STAND_LEFT] = new Animation(images,walkAnimationSpeed);
        images[0] = spriteSheet.getSprite(2,1);
        animations[Char.STAND_RIGHT] = new Animation(images,walkAnimationSpeed);
        protraitKey = "paladin.png";
    }
}
