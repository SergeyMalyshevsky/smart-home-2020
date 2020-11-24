package ru.sbt.mipt.oop.sensor.command.senders;

import ru.sbt.mipt.oop.sensor.command.SensorCommand;

public interface CommandSender {
    void send(SensorCommand command);
}
