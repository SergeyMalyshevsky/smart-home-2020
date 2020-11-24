package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.sensor.event.SensorEvent;
import ru.sbt.mipt.oop.components.SmartHome;

import static ru.sbt.mipt.oop.sensor.event.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.sensor.event.SensorEventType.DOOR_OPEN;

public class DoorEventHandler implements EventHandler {
    public DoorEventHandler() {

    }

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    if (event.getType() == DOOR_OPEN) {
                        door.setOpen();
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                    }
                    if (event.getType() == DOOR_CLOSED) {
                        door.setClosed();
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                    }
                }
            }
        }
    }
}
