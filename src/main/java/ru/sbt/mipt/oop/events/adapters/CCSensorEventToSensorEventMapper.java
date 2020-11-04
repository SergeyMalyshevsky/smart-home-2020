package ru.sbt.mipt.oop.events.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.sensor.event.SensorEvent;
import ru.sbt.mipt.oop.sensor.event.SensorEventType;

import java.util.HashMap;
import java.util.Map;

import static ru.sbt.mipt.oop.sensor.event.SensorEventType.*;

public class CCSensorEventToSensorEventMapper {
    Map<String, SensorEventType> map;

    public CCSensorEventToSensorEventMapper(Map<String, SensorEventType> map) {
        this.map = map;
    }

    private SensorEventType getSensorEventType(CCSensorEvent event) {
        String type = event.getEventType();
        if (map.containsKey(type)) {
            return map.get(type);
        }
        return null;
    }

    public SensorEvent convert(CCSensorEvent event) {
        SensorEventType sensorEventType = getSensorEventType(event);
        if (sensorEventType != null) {
            return new SensorEvent(sensorEventType, event.getObjectId());
        }
        return null;
    }
}
