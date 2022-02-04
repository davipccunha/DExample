package io.github.davipccunha.dexample.commands;

import io.github.davipccunha.dexample.ExamplePlugin;
import io.github.davipccunha.dexample.models.EconomyUser;
import me.pedro.aguiar.library.builder.command.builder.CommandBuilder;
import me.pedro.aguiar.library.builder.command.info.CommandInfo;
import me.pedro.aguiar.library.builder.command.mirror.Sender;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

@CommandInfo(
        name = "coins",
        console = true
)

public class CoinsCommand extends CommandBuilder {

    private final ExamplePlugin instance;

    public CoinsCommand(ExamplePlugin instance) {
        this.instance = instance;

        register(getInfo(this));
    }

    @Override
    public void run(Sender sender, String[] args) {
        EconomyUser economyUser = instance.getEconomyUserCache().get(sender.getName());

        if (economyUser == null) return;

        EconomyUser target;

        switch (args[0]) {
            case "add":
                if (args.length < 3) return;
                economyUser.addCoins(Double.parseDouble(args[2]));
                target = instance.getEconomyUserCache().get(args[1]);
                break;

            case "remove":
                if (args.length < 3) return;
                economyUser.removeCoins(Double.parseDouble(args[2]));
                target = instance.getEconomyUserCache().get(args[1]);
                break;

            case "set":
                if (args.length < 3) return;
                economyUser.setCoins(Double.parseDouble(args[2]));
                target = instance.getEconomyUserCache().get(args[1]);
                break;

            default:
                target = instance.getEconomyUserCache().get(args[0]);
                break;
        }

        if (target == null) return;

        sender.sendMessage(ChatColor.GREEN + "Current balance for " + target.getName() + ": " + target.getCoins());
    }

    @Override
    public void runWithoutArguments(Sender sender) {
        EconomyUser economyUser = instance.getEconomyUserCache().get(sender.getName());

        if (economyUser == null) return;

        sender.sendMessage(ChatColor.GREEN + "Coins: " + economyUser.getCoins());
    }
}
