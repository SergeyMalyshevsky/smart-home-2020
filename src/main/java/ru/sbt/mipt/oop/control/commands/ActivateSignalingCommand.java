package ru.sbt.mipt.oop.control.commands;

import ru.sbt.mipt.oop.signaling.Signaling;

public class ActivateSignalingCommand implements Command{
    private final Signaling signaling;
    private String code;

    public ActivateSignalingCommand(Signaling signaling, String code) {
        this.signaling = signaling;
        this.code = code;
    }

    @Override
    public void execute() {
        signaling.activate(code);
    }
}
