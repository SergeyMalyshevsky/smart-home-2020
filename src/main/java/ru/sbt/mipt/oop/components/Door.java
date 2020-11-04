package ru.sbt.mipt.oop.components;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.actions.Actionable;

public class Door implements Actionable {
    private final String id;
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setOpen() {
        isOpen = true;
    }

    public void setClosed() {
        isOpen = false;
    }

    @Override
    public void execute(Action action) {
        action.act(this);
    }
}
