package ru.sbt.mipt.oop.events.manager;

import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.events.handlers.IEventHandler;
import ru.sbt.mipt.oop.sensor.event.SensorEvent;
import ru.sbt.mipt.oop.signaling.Signaling;

import java.util.ArrayList;
import java.util.List;

public class EventManager implements IEventManager {
    private SmartHome smartHome;
    private List<IEventHandler> eventHandlers = new ArrayList<>();

    public EventManager(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public EventManager(SmartHome smartHome, List<IEventHandler> eventHandlers) {
        this.smartHome = smartHome;
        this.eventHandlers = eventHandlers;
    }

    @Override
    public void setSmartHome(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void addHandler(IEventHandler eventHandler) {
        this.eventHandlers.add(eventHandler);
    }

    @Override
    public void processEvent(SensorEvent sensorEvent) {
        for (IEventHandler eventHandler : eventHandlers) {
            eventHandler.processEvent(smartHome, sensorEvent);
        }
    }
}
