package ru.sbt.mipt.oop.control.commands;

import ru.sbt.mipt.oop.signaling.Signaling;

public class SignalingSetAlarmCommand implements Command{
    private final Signaling signaling;

    public SignalingSetAlarmCommand(Signaling signaling) {
        this.signaling = signaling;
    }

    @Override
    public void execute() {
        signaling.alarm();
    }
}
