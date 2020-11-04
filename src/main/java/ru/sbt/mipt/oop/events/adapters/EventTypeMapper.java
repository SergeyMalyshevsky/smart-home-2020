package ru.sbt.mipt.oop.events.adapters;

import ru.sbt.mipt.oop.sensor.event.SensorEventType;

import java.util.HashMap;
import java.util.Map;

import static ru.sbt.mipt.oop.sensor.event.SensorEventType.*;

public class EventTypeMapper {
    Map<String, SensorEventType> map = new HashMap<>();

    public EventTypeMapper(Map<String, SensorEventType> map) {
        this.map = map;
    }

    public EventTypeMapper() {
        map.put("LightIsOn", LIGHT_ON);
        map.put("LightIsOff", LIGHT_OFF);
        map.put("DoorIsOpen", DOOR_OPEN);
        map.put("DoorIsClosed", DOOR_CLOSED);
        map.put("DoorIsUnlocked", DOOR_OPEN);
        map.put("DoorIsLocked", DOOR_CLOSED);
    }

    public Map<String, SensorEventType> getMap() {
        return map;
    }
}
