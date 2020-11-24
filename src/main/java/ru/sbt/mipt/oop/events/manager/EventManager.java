package ru.sbt.mipt.oop.events.manager;

import ru.sbt.mipt.oop.events.handlers.EventHandler;
import ru.sbt.mipt.oop.sensor.event.SensorEvent;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.sensor.senders.SensorEventSender;

public interface EventManager {
    void addHandler(EventHandler handler);
    void handleEvents(SensorEventSender sensorEventSender);
}
