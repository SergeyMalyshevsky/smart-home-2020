package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.sensor.event.SensorEvent;

public class DoorCloseAction implements Action {
    private final SensorEvent event;

    public DoorCloseAction(SensorEvent event) {
        this.event = event;
    }

    @Override
    public void act(Actionable actionable) {
        if (actionable instanceof Door) {
            Door door = (Door) actionable;
            if (door.getId().equals(event.getObjectId())) {
                door.setClosed();
                System.out.println("Door " + door.getId() + " was closed.");
            }
        }
    }
}
