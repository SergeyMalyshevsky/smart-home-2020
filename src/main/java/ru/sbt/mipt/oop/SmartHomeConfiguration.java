package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.config.loaders.SmartHomeLoader;
import ru.sbt.mipt.oop.config.loaders.JsonSmartHomeLoader;
import ru.sbt.mipt.oop.control.SmartHomeRemoteControl;
import ru.sbt.mipt.oop.control.commands.*;
import ru.sbt.mipt.oop.events.adapters.CCEventHandlerAdapter;
import ru.sbt.mipt.oop.events.adapters.CCSensorEventToSensorEventMapper;
import ru.sbt.mipt.oop.events.adapters.EventTypeMapper;
import ru.sbt.mipt.oop.events.handlers.DoorEventHandler;
import ru.sbt.mipt.oop.events.handlers.HallDoorEventHandler;
import ru.sbt.mipt.oop.events.handlers.EventHandler;
import ru.sbt.mipt.oop.events.handlers.LightEventHandler;
import ru.sbt.mipt.oop.events.manager.EventManager;
import ru.sbt.mipt.oop.events.manager.EventManagerImpl;
import ru.sbt.mipt.oop.sensor.event.SensorEventType;
import ru.sbt.mipt.oop.signaling.Signaling;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.sbt.mipt.oop.sensor.event.SensorEventType.*;

@Configuration
public class SmartHomeConfiguration {
    @Bean
    SmartHome smartHome() throws IOException {
        String INPUT_CONFIG_FILE = "smart-home-1.js";
        SmartHomeLoader smartHomeLoader = new JsonSmartHomeLoader(INPUT_CONFIG_FILE);
        return smartHomeLoader.load();
    }

    @Bean
    EventHandler lightEventHandler() {
        return new LightEventHandler();
    }

    @Bean
    EventHandler doorEventHandler() {
        return new DoorEventHandler();
    }

    @Bean
    EventHandler hallDoorEventHandler() {
        return new HallDoorEventHandler();
    }

    @Bean
    EventManager eventManager(List<EventHandler> eventHandlers) throws IOException {
        return new EventManagerImpl(smartHome(), eventHandlers);
    }

    @Bean
    EventTypeMapper eventTypeMapper() {
        Map<String, SensorEventType> map = new HashMap<>();
        map.put("LightIsOn", LIGHT_ON);
        map.put("LightIsOff", LIGHT_OFF);
        map.put("DoorIsOpen", DOOR_OPEN);
        map.put("DoorIsClosed", DOOR_CLOSED);
        map.put("DoorIsUnlocked", DOOR_OPEN);
        map.put("DoorIsLocked", DOOR_CLOSED);
        return new EventTypeMapper(map);
    }

    @Bean
    CCSensorEventToSensorEventMapper eventMapper(EventTypeMapper mapper) {
        return new CCSensorEventToSensorEventMapper(mapper);
    }

    @Bean
    CCEventHandlerAdapter cCEventHandlerAdapter(EventManager eventManager, CCSensorEventToSensorEventMapper eventMapper) {
        return new CCEventHandlerAdapter(eventManager, eventMapper);
    }

    @Bean
    @Autowired
    SensorEventsManager sensorEventsManager(CCEventHandlerAdapter cCEventHandlerAdapter) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(cCEventHandlerAdapter);
        return sensorEventsManager;
    }

    @Bean
    Signaling signaling() {
        return new Signaling();
    }

    @Bean
    String signalingCode() {
        return "123456";
    }

    @Bean
    public Command activateSignalingCommand() {
        return new ActivateSignalingCommand(signaling(), signalingCode());
    }

    @Bean
    public Command closeHallDoorCommand() throws IOException {
        return new CloseHallDoorCommand(smartHome());
    }

    @Bean
    public Command setOffAllLightsCommand() throws IOException {
        return new SetOffAllLightsCommand(smartHome());
    }

    @Bean
    public Command setOnAllLightsCommand() throws IOException {
        return new SetOnAllLightsCommand(smartHome());
    }

    @Bean
    public Command setOnHallLightCommand() throws IOException {
        return new SetOnHallLightCommand(smartHome());
    }

    @Bean
    public Command signalingSetAlarmCommand() {
        return new SignalingSetAlarmCommand(signaling());
    }

    @Bean
    public Map<String, Command> buttonCommandMap() throws IOException{
        Map<String, Command> buttonCommandMap = new HashMap<>();
        buttonCommandMap.put("A", activateSignalingCommand());
        buttonCommandMap.put("B", closeHallDoorCommand());
        buttonCommandMap.put("C", setOffAllLightsCommand());
        buttonCommandMap.put("D", setOnAllLightsCommand());
        buttonCommandMap.put("1", setOnHallLightCommand());
        buttonCommandMap.put("2", signalingSetAlarmCommand());
        return buttonCommandMap;
    }

    @Bean
    RemoteControlRegistry remoteControlRegistry() {
        return new RemoteControlRegistry();
    }

    @Bean
    String rcId() {
        return "007";
    }

    @Bean
    SmartHomeRemoteControl smartHomeRemoteControl(Map<String, Command> commandsMap) throws IOException{
        SmartHomeRemoteControl remoteControl = new SmartHomeRemoteControl(buttonCommandMap());
        remoteControlRegistry().registerRemoteControl(remoteControl, rcId());
        return remoteControl;
    }
}
