package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.sensor.event.SensorEvent;

public class DoorOpenAction implements Action{
    private final SensorEvent event;

    public DoorOpenAction(SensorEvent event) {
        this.event = event;
    }

    @Override
    public void act(Actionable actionable) {
        if(actionable instanceof Door) {
            Door door = (Door) actionable;
            if (door.getId().equals(event.getObjectId())) {
                door.setOpen();
                System.out.println("Door " + door.getId() + " was opened.");
            }
        }
    }
}
