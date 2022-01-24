package io.github.davipccunha.dexample.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@RequiredArgsConstructor
public class JSONFileUtil<V> {

    @Getter
    private final Gson gson = new GsonBuilder().create();

    private final String folder;

    private final JavaPlugin plugin;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void createFolder(String name) {
        final File folder = new File(plugin.getDataFolder() + File.separator + name);

        if (!folder.exists())
            folder.mkdirs();
    }

    @SneakyThrows
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void saveJsonFile(String name, V value) {
        final File file = getJsonFile(name, plugin);

        if (!file.exists())
            file.createNewFile();

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(gson.toJson(value));

            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public V getJson(String name) {
        final File file = getJsonFile(name, plugin);

        V user;

        if (file.exists()) {
            try (FileReader fileReader = new FileReader(file);
                 JsonReader jsonReader = new JsonReader(fileReader)) {
                user = gson.fromJson(jsonReader, Class.class);
            }

            return user;
        }

        return null;
    }

    private File getJsonFile(String name, JavaPlugin plugin) {
        return new File(plugin.getDataFolder() + File.separator + folder, name + ".json");
    }

}