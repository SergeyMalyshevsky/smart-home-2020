package ru.sbt.mipt.oop.sensor.command.senders;

import ru.sbt.mipt.oop.sensor.command.SensorCommand;

public class CommandSenderImpl implements CommandSender {
    @Override
    public void send(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}
