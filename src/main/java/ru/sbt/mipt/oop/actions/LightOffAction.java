package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.sensor.event.SensorEvent;

public class LightOffAction implements Action{
    private final SensorEvent event;

    public LightOffAction(SensorEvent event) {
        this.event = event;
    }

    @Override
    public void act(Actionable actionable) {
        if(actionable instanceof Light) {
            Light light = (Light) actionable;
            if (light.getId().equals(event.getObjectId())) {
                light.setOff();
                System.out.println("Light " + light.getId() + " was turned off.");
            }
        }
    }
}
