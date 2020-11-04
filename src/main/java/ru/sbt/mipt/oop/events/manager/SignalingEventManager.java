package ru.sbt.mipt.oop.events.manager;

import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.events.handlers.IEventHandler;
import ru.sbt.mipt.oop.sensor.event.SensorEvent;
import ru.sbt.mipt.oop.signaling.Signaling;

import static ru.sbt.mipt.oop.sensor.event.SensorEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.sensor.event.SensorEventType.ALARM_DEACTIVATE;

public class SignalingEventManager implements IEventManager {
    private final IEventManager eventManager;
    private final Signaling signaling;
    private final String signalingCode;

    public SignalingEventManager(IEventManager eventManager, String signalingCode) {
        this.eventManager = eventManager;
        this.signaling = new Signaling();
        this.signalingCode = signalingCode;
    }

    private void processSignalingEvent(SensorEvent event) {
        if (event.getType() == ALARM_ACTIVATE) {
            signaling.activate(signalingCode);
        }
        if (event.getType() == ALARM_DEACTIVATE) {
            signaling.deactivate(signalingCode);
        }
    }

    @Override
    public void setSmartHome(SmartHome smartHome) {
        this.eventManager.setSmartHome(smartHome);
    }

    @Override
    public void addHandler(IEventHandler handler) {
        this.eventManager.addHandler(handler);
    }

    @Override
    public void processEvent(SensorEvent event) {
        if (event.getType() == ALARM_ACTIVATE || event.getType() == ALARM_DEACTIVATE) {
            processSignalingEvent(event);
        } else if (signaling.isAlarmed()) {
            System.out.println("Sending sms");
        } else if (signaling.isActivated()) {
            signaling.alarm();
            System.out.println("Sending sms");
        } else {
            eventManager.processEvent(event);
        }
    }
}
