package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.sensor.event.SensorEvent;

import static ru.sbt.mipt.oop.sensor.event.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.sensor.event.SensorEventType.LIGHT_ON;

public class LightEventHandler implements EventHandler {

    public LightEventHandler() {

    }

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    if (event.getType() == LIGHT_ON) {
                        light.setOn();
                        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
                    }
                    if (event.getType() == LIGHT_OFF) {
                        light.setOff();
                        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
                    }
                }
            }
        }
    }
}
