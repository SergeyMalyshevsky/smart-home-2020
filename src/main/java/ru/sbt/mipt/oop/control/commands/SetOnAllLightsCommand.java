package ru.sbt.mipt.oop.control.commands;

import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.SmartHome;

public class SetOnAllLightsCommand implements Command{
    SmartHome smartHome;

    public SetOnAllLightsCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(component -> {
            if (component instanceof Light) {
                Light light = (Light) component;
                light.setOn();
            }
        });
    }
}
