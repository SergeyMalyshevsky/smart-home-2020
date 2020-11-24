package ru.sbt.mipt.oop.signaling.notifier;

public class DummySmsSender implements Notifier{
    @Override
    public void sendSMS() {
        System.out.println("Sending sms");
    }
}
