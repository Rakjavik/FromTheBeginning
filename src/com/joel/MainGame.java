package com.joel;

import com.joel.characters.Character;
import com.joel.characters.DwarfGuard;
import com.joel.maps.Map;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 430009998 on 10/28/2015.
 */
public class MainGame extends BasicGame {

    private List<Character> characters;
    public static Map map;
    public static int tilesize = 25;

    public MainGame() {
        super("From the Beginning");
    }

    @Override
    public void keyPressed(int key, char c) {
        if(key == Input.KEY_M) {

        }
    }

    @Override
    public void keyReleased(int key, char c) {
        super.keyReleased(key, c);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        characters = new ArrayList<Character>();
        for (int count = 0; count < 3; count++) {
            DwarfGuard dwarfGuard = new DwarfGuard();
            characters.add(dwarfGuard);
        }
        map = new Map("Berry Garden","images/bgp.png");
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        for (int count = 0; count < characters.size(); count++) {
            characters.get(count).update(i);
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

        map.render(graphics);
        for (Shape shape : map.getCollisionPoints()) {
            graphics.draw(shape);
        }
        for (int count = 0; count < characters.size(); count++) {
            characters.get(count).render(graphics);
        }
    }

    public static void main(String[] args) {
        try {
            AppGameContainer container = new AppGameContainer(new MainGame());
            container.setDisplayMode(640,480,false);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace(System.out);
        }
    }
}
