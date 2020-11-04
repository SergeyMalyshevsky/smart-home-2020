package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.sensor.event.SensorEvent;
import ru.sbt.mipt.oop.components.SmartHome;

public interface IEventHandler {
    void processEvent(SmartHome smartHome, SensorEvent event);
}
