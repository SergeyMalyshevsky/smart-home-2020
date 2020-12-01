package ru.sbt.mipt.oop.control;

import rc.RemoteControl;
import ru.sbt.mipt.oop.control.commands.Command;

import java.util.Map;

public class SmartHomeRemoteControl implements RemoteControl {
    private final Map<String, Command> commandsMap;

    public SmartHomeRemoteControl(Map<String, Command> commandsMap) {
        this.commandsMap = commandsMap;
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if (commandsMap.containsKey(buttonCode)) {
            commandsMap.get(buttonCode).execute();
        }
    }
}
