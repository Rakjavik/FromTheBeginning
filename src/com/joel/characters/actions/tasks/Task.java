package com.joel.characters.actions.tasks;

import com.joel.Updateable;
import com.joel.characters.Char;
import com.joel.characters.actions.IdleEvent;
import com.joel.characters.actions.tasks.helpers.TaskHelper;

/**
 * Created by 430009998 on 11/1/2015.
 */
public abstract class Task implements Updateable {

    public int priority;

    protected Char assignedCharacter;
    protected int step = 0;
    private String name;
    private int timeSinceLastUpdate = 0;
    protected boolean waitingForEvent = false;
    protected boolean taskComplete = false;
    protected Object target;

    public Task(String name) {
        this.name = name;
    }


    @Override
    public void update(int delta) {
        timeSinceLastUpdate += delta;
        // Assign character to task //
        if(timeSinceLastUpdate > priority && assignedCharacter == null) {
            assignedCharacter = TaskHelper.findCharForTask(this);
            timeSinceLastUpdate = 0;
            return;
        }
        if(assignedCharacter != null && timeSinceLastUpdate > assignedCharacter.getSpeed()) {
            // After each event completes it goes to Idle, wait for that event //
            if (assignedCharacter.getCurrentEvent() instanceof IdleEvent) {
                step++;
                waitingForEvent = false;
            }
        }
    }

    public void cleanup() {
        if (assignedCharacter != null)
            assignedCharacter.setFreeForAssignment(true);
    }

    public boolean isTaskComplete() {
        return taskComplete;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Char getAssignedCharacter() {
        return assignedCharacter;
    }

}
