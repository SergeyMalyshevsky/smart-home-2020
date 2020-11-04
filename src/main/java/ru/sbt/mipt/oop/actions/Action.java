package ru.sbt.mipt.oop.actions;

@FunctionalInterface
public interface Action {
    void act(Actionable actionable);
}
