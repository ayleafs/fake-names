package me.leafs.fakename.utils;

import com.google.gson.*;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class ConfigHandler {
    @Getter private final File configFile;

    @Getter @Setter
    private String fakeName = null;

    public ConfigHandler(File configFile) {
        this.configFile = configFile;
    }

    @SneakyThrows
    public void populate() {
        if (fakeName == null) {
            // write an empty json object
            Files.write(configFile.toPath(), "{}".getBytes(StandardCharsets.UTF_8));
            return;
        }

        JsonObject object = new JsonObject();
        object.add("fake_name", new JsonPrimitive(fakeName));

        // convert the json object
        Gson gson = new Gson();
        String strJson = gson.toJson(object);

        try {
            // write the json bytes to the config file
            Files.write(configFile.toPath(), strJson.getBytes(StandardCharsets.UTF_8));
        } catch (IOException ignored) { } // ignored, get fucked
    }

    @SneakyThrows
    public void read() {
        if (!configFile.exists()) {
            // can't read what doesn't exist
            populate();
            return;
        }

        // create a read stream of the config file
        InputStream stream = Files.newInputStream(configFile.toPath());

        // time to parse JSON (over-complicating this because there's literally
        // only one field in the config but I don't care, I'm elon musk)
        JsonObject object;
        try {
            JsonParser parser = new JsonParser();
            object = parser.parse(new InputStreamReader(stream)).getAsJsonObject();
        } catch (JsonParseException e) {
            // try to re-write the file
            populate();
            return;
        }

        // set the lastFakeName to whatever that is
        fakeName = object.get("fake_name").getAsString();
    }
}
