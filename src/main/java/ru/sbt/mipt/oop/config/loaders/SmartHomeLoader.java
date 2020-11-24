package ru.sbt.mipt.oop.config.loaders;

import ru.sbt.mipt.oop.components.SmartHome;

import java.io.IOException;

public interface SmartHomeLoader {
    SmartHome load() throws IOException;
}
