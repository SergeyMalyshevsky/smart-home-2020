package ru.sbt.mipt.oop.events.manager;

import ru.sbt.mipt.oop.events.handlers.EventHandler;
import ru.sbt.mipt.oop.sensor.event.SensorEvent;
import ru.sbt.mipt.oop.sensor.senders.SensorEventSender;
import ru.sbt.mipt.oop.signaling.Signaling;
import ru.sbt.mipt.oop.signaling.notifier.DummySmsSender;

import static ru.sbt.mipt.oop.sensor.event.SensorEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.sensor.event.SensorEventType.ALARM_DEACTIVATE;

public class SignalingEventManager implements EventManager {
    private final EventManager eventManager;
    private final Signaling signaling;
    private final String signalingCode;

    public SignalingEventManager(EventManager eventManager, String signalingCode) {
        this.eventManager = eventManager;
        this.signaling = new Signaling();
        this.signalingCode = signalingCode;
    }

    @Override
    public void addHandler(EventHandler handler) {
        this.eventManager.addHandler(handler);
    }

    @Override
    public void handleEvents(SensorEventSender sensorEventSender) {
        SensorEvent event;

        while (true) {
            // начинаем цикл обработки событий
            event = sensorEventSender.getNextSensorEvent();

            if (event == null) {
                return;
            }
            this.processEvent(event);
        }
    }

    @Override
    public void processEvent(SensorEvent event) {
        DummySmsSender dummySmsSender = new DummySmsSender();
        if (event.getType() == ALARM_ACTIVATE || event.getType() == ALARM_DEACTIVATE) {
            if (event.getType() == ALARM_ACTIVATE) {
                signaling.activate(signalingCode);
            }
            if (event.getType() == ALARM_DEACTIVATE) {
                signaling.deactivate(signalingCode);
            }
        } else if (signaling.isAlarmed()) {
            dummySmsSender.sendSMS();
        } else if (signaling.isActivated()) {
            signaling.alarm();
            dummySmsSender.sendSMS();
        } else {
            eventManager.processEvent(event);
        }
    }
}
