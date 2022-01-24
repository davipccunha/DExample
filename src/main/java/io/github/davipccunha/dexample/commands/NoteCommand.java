package io.github.davipccunha.dexample.commands;

import io.github.davipccunha.dexample.ExamplePlugin;
import io.github.davipccunha.dexample.models.Note;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class NoteCommand implements CommandExecutor {

    private final ExamplePlugin instance;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        if (args.length <= 1) return false;

        String message;
        String name = args[1];

        switch (args[0]) {
            case "create":
                if (args.length == 2) return false;

                message = instance.getNoteManagement().createNote(name, sender.getName(), noteContent(args, 2))
                        ? "Note created!"
                        : "Note already exists.";

                break;

            case "delete":
                message = instance.getNoteManagement().deleteNote(name) ? "Note deleted!" : "Note not found.";

                break;

            case "update":
                if (args.length == 2) return false;

                message = instance.getNoteManagement().updateNote(name, noteContent(args, 2)) ? "Note updated"
                        : "Note not found";

                break;

            case "read":
                Note note = instance.getNoteManagement().getNote(name);
                message = note == null ? "Note not found" : note.getContent();

                break;

            default:
                message = "Subcommand does not exist";
                break;
        }

        sender.sendMessage(message);

        return false;
    }

    @SuppressWarnings("SameParameterValue")
    private String noteContent(String[] args, int index) {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = index; i < args.length; i++) {
            stringBuilder.append(args[i]).append(" ");
        }

        return stringBuilder.toString();
    }
}
