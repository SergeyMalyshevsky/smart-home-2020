package ru.sbt.mipt.oop.config.savers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sbt.mipt.oop.components.SmartHome;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonSmartHomeSaver implements ISmartHomeSaver {
    private final String outputFilename;

    public JsonSmartHomeSaver(String outputFilename) {
        this.outputFilename = outputFilename;
    }

    @Override
    public void saveSmartHome(SmartHome smartHome) throws RuntimeException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(smartHome);
        System.out.println(jsonString);
        Path path = Paths.get(outputFilename);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
