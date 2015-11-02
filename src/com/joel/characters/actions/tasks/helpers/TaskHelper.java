package com.joel.characters.actions.tasks.helpers;

import com.joel.MainGame;
import com.joel.characters.Char;
import com.joel.characters.actions.tasks.Task;

import java.util.List;

/**
 * Created by 430009998 on 11/1/2015.
 */
public class TaskHelper {
    public static Char findCharForTask(Task task) {
        List<Char> charList = MainGame.getCharacters();
        for(Char character : charList) {
            // TODO check character job enabled
            // Character is idle //
            if (character.isFreeForAssignment()) {
                character.setFreeForAssignment(false);
                return character;
            }
        }
        return null;
    }
    public static int getIndexByObject(List list, Object object) {
        for(int count = 0; count < list.size(); count++) {
            if(list.get(count).equals(object)) {
                return count;
            }
        }
        return -1;
    }
}
