package ru.sbt.mipt.oop.sensor.command.senders;

import ru.sbt.mipt.oop.sensor.command.SensorCommand;

public interface ICommandSender {
    void send(SensorCommand command);
}
