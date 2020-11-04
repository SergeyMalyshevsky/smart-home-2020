package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.actions.DoorCloseAction;
import ru.sbt.mipt.oop.actions.DoorOpenAction;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.sensor.event.SensorEvent;
import ru.sbt.mipt.oop.components.SmartHome;

import static ru.sbt.mipt.oop.sensor.event.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.sensor.event.SensorEventType.DOOR_OPEN;

public class DoorEventHandler implements IEventHandler {
    public DoorEventHandler() {

    }

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        smartHome.execute(actionable -> {
            if (actionable instanceof Door) {
                Door door = (Door) actionable;
                if (door.getId().equals(event.getObjectId())) {
                    if (event.getType() == DOOR_OPEN) {
                        Action action = new DoorOpenAction(event);
                        door.execute(action);
                    }
                    if (event.getType() == DOOR_CLOSED) {
                        Action action = new DoorCloseAction(event);
                        door.execute(action);
                    }
                }

            }

        });
    }
}
