package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.config.loaders.SmartHomeLoader;
import ru.sbt.mipt.oop.config.loaders.JsonSmartHomeLoader;
import ru.sbt.mipt.oop.events.handlers.DoorEventHandler;
import ru.sbt.mipt.oop.events.handlers.HallDoorEventHandler;
import ru.sbt.mipt.oop.events.handlers.LightEventHandler;
import ru.sbt.mipt.oop.events.manager.EventManagerImpl;
import ru.sbt.mipt.oop.events.manager.EventManager;
import ru.sbt.mipt.oop.sensor.event.SensorEvent;
import ru.sbt.mipt.oop.sensor.senders.SensorEventSender;
import ru.sbt.mipt.oop.sensor.senders.RandomSensorEventSender;

import java.io.IOException;

public class Application {
    private final EventManager eventManager;
    private final SensorEventSender sensorEventSender;

    public Application(EventManager eventManager, SensorEventSender sensorEventSender) {
        this.eventManager = eventManager;
        this.sensorEventSender = sensorEventSender;
    }

    public void run() throws IOException {
        eventManager.handleEvents(sensorEventSender);
    }

    public static void main(String... args) throws IOException {
        String INPUT_CONFIG_FILE = "smart-home-1.js";

        // считываем состояние дома из файла
        SmartHomeLoader smartHomeLoader = new JsonSmartHomeLoader(INPUT_CONFIG_FILE);
        SmartHome smartHome = smartHomeLoader.load();

        EventManager eventManager = new EventManagerImpl(smartHome);
        eventManager.addHandler(new LightEventHandler());
        eventManager.addHandler(new DoorEventHandler());
        eventManager.addHandler(new HallDoorEventHandler());
        SensorEventSender sensorEventSender = new RandomSensorEventSender();

        Application application = new Application(eventManager, sensorEventSender);
        application.run();
    }

}
