package ru.sbt.mipt.oop.signaling;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SignalingTest {

    @Test
    void checkIsAlarmWhenIncorrectCode() {
        Signaling signaling = new Signaling();
        String code = "real_code";
        signaling.activate(code);
        signaling.deactivate("bad_code");
        assertTrue(signaling.isAlarmed());
    }

    @Test
    void checkIsDeactivateWhenCodeIsCorrect() {
        Signaling signaling = new Signaling();
        String code = "real_code";
        signaling.activate(code);
        signaling.deactivate(code);
        assertTrue(signaling.isDeactivated());
    }

    @Test
    void checkActivate() {
        Signaling signaling = new Signaling();
        String code = "real_code";
        signaling.activate(code);
        assertTrue(signaling.isActivated());
    }

    @Test
    void checkSpecialStopAlarmMethod() {
        Signaling signaling = new Signaling();
        String code = "real_code";
        signaling.activate(code);
        signaling.deactivate("bad_code");
        assertTrue(signaling.isAlarmed());
        signaling.stopAlarm();
        assertTrue(signaling.isDeactivated());
    }

    @Test
    void checkNotAlarmWhenDeactivated() {
        Signaling signaling = new Signaling();
        assertTrue(signaling.isDeactivated());
        signaling.deactivate("test_code");
        assertTrue(signaling.isDeactivated());
    }
}