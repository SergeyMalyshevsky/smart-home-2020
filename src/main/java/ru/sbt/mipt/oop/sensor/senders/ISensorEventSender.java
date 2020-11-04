package ru.sbt.mipt.oop.sensor.senders;

import ru.sbt.mipt.oop.sensor.event.SensorEvent;

public interface ISensorEventSender {
    SensorEvent getNextSensorEvent();
}
