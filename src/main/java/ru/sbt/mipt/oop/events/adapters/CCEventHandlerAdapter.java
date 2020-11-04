package ru.sbt.mipt.oop.events.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.events.manager.EventManager;
import ru.sbt.mipt.oop.sensor.event.SensorEvent;

public class CCEventHandlerAdapter implements EventHandler {
    private EventManager eventManager;
    private CCSensorEventToSensorEventMapper mapper;

    public CCEventHandlerAdapter(EventManager eventManager, CCSensorEventToSensorEventMapper mapper) {
        this.eventManager = eventManager;
        this.mapper = mapper;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEvent sensorEvent = mapper.convert(event);
        if (event != null) {
            eventManager.processEvent(sensorEvent);
        }
    }
}
