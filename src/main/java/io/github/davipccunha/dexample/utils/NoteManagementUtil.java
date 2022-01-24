package io.github.davipccunha.dexample.utils;

import com.google.gson.Gson;
import io.github.davipccunha.dexample.ExamplePlugin;
import io.github.davipccunha.dexample.models.Note;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class NoteManagementUtil {

    private final ExamplePlugin instance;

    private final Map<String, Note> notes = new HashMap<>();

    public boolean createNote(String name, String author, String content) {
        if (notes.get(name) != null) return false;

        notes.put(name, new Note(author, content));

        saveNote(name);

        return true;
    }

    public void load(String name, Note note) {
        notes.put(name, note);
    }

    public boolean deleteNote(String name) {
        if (notes.get(name) == null) return false;

        notes.remove(name);
        saveNote(name);

        return true;
    }

    public Note getNote(String name) {
        return notes.get(name);
    }

    public boolean updateNote(String name, String newContent) {
        if (notes.get(name) == null) return false;

        Note newNote = new Note(notes.get(name).getAuthor(), newContent);
        notes.put(name, newNote);

        saveNote(name);

        return true;
    }

    @SneakyThrows
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void saveNote(String name) {
        Gson gson = new Gson();
        File file = new File(instance.getDataFolder() + String.format("/Notes/%s.json", name));

        file.createNewFile();

        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(notes.get(name), writer);

            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
