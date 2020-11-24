package ru.sbt.mipt.oop.sensor.senders;

import ru.sbt.mipt.oop.sensor.event.SensorEvent;
import ru.sbt.mipt.oop.sensor.event.SensorEventType;

public class RandomSensorEventSender implements SensorEventSender {
    @Override
    public SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (SensorEventType.values().length * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));

        return new SensorEvent(sensorEventType, objectId);
    }
}
