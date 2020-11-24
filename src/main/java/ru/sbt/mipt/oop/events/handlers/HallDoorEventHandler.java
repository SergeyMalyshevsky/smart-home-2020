package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.sensor.command.CommandType;
import ru.sbt.mipt.oop.sensor.command.SensorCommand;
import ru.sbt.mipt.oop.sensor.command.senders.CommandSenderImpl;
import ru.sbt.mipt.oop.sensor.command.senders.CommandSender;
import ru.sbt.mipt.oop.sensor.event.SensorEvent;

import static ru.sbt.mipt.oop.sensor.event.SensorEventType.DOOR_CLOSED;

public class HallDoorEventHandler implements EventHandler {
    public HallDoorEventHandler() {

    }

    private void lightOffAllRooms(SmartHome smartHome) {
        CommandSender commandSender = new CommandSenderImpl();
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOff();
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                commandSender.send(command);
            }
        }
    }

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    if (event.getType() == DOOR_CLOSED) {
                        door.setClosed();
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                        // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                        if (room.getName().equals("hall")) {
                            lightOffAllRooms(smartHome);
                        }
                    }
                }
            }
        }
    }
}
