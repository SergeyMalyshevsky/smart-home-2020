package ru.sbt.mipt.oop.config.savers;

import ru.sbt.mipt.oop.components.SmartHome;

public interface SmartHomeSaver {
    void saveSmartHome(SmartHome smartHome) throws RuntimeException;
}
