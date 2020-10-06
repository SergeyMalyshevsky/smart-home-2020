package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.HomeComponent;

public class DoorOpenAction implements Action{
    @Override
    public void act(HomeComponent homeComponent) {
        if(homeComponent instanceof Door) {
            Door door = (Door) homeComponent;
            door.setOpen();
            System.out.println("Door " + door.getId() + " was opened.");
        }
    }
}
