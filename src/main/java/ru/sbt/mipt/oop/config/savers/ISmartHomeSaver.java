package ru.sbt.mipt.oop.config.savers;

import ru.sbt.mipt.oop.components.SmartHome;

public interface ISmartHomeSaver {
    void saveSmartHome(SmartHome smartHome) throws RuntimeException;
}
