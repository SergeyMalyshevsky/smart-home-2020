package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.actions.HallDoorCloseAction;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.sensor.event.SensorEvent;

import static ru.sbt.mipt.oop.sensor.event.SensorEventType.DOOR_CLOSED;

public class HallDoorEventHandler implements IEventHandler {
    public HallDoorEventHandler() {

    }

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        smartHome.execute(actionable -> {
            if (actionable instanceof Door) {
                Door door = (Door) actionable;
                if (door.getId().equals(event.getObjectId())) {
                    if (event.getType() == DOOR_CLOSED) {
                        Action action = new HallDoorCloseAction(event);
                        smartHome.execute(action);
                    }
                }

            }
        });
    }
}
