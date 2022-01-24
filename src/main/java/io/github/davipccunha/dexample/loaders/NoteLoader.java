package io.github.davipccunha.dexample.loaders;

import com.google.gson.Gson;
import io.github.davipccunha.dexample.ExamplePlugin;
import io.github.davipccunha.dexample.models.Note;

import java.io.*;
import java.util.Objects;

public class NoteLoader {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void load(ExamplePlugin instance) {
        Gson gson = new Gson();

        File file = new File(instance.getDataFolder() + "/Notes");
        file.mkdirs();

        for (File f : Objects.requireNonNull(file.listFiles())) {
            try (Reader reader = new FileReader(f)) {

                Note note = gson.fromJson(reader, Note.class);

                instance.getNoteManagement().load(f.getName().replace(".json", ""), note);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
