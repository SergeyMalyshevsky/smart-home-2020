package ru.sbt.mipt.oop.control.commands;

import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;

public class SetOnHallLightCommand implements Command{
    private final SmartHome smartHome;

    public SetOnHallLightCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(component -> {
            if (component instanceof Room) {
                Room room = (Room) component;
                if (room.getName().equals("hall")) {
                    room.execute(element -> {
                        if (element instanceof Light) {
                            Light light = (Light) element;
                            light.setOff();
                        }
                    });
                }
            }
        });
    }
}
