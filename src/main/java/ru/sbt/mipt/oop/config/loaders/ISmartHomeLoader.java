package ru.sbt.mipt.oop.config.loaders;

import ru.sbt.mipt.oop.components.SmartHome;

import java.io.IOException;

public interface ISmartHomeLoader {
    SmartHome load() throws RuntimeException, IOException;
}
