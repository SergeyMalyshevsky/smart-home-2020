package ru.sbt.mipt.oop.components;

public class Door {
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
}
