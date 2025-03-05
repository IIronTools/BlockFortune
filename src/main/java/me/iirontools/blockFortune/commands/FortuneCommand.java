package me.iirontools.blockFortune.commands;

import me.iirontools.blockFortune.configs.MainConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class FortuneCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {

            try {
                MainConfig.getInstance().load();
            } catch (IllegalArgumentException e) {
                sender.sendMessage(ChatColor.RED + "Wystąpił błąd w configu!");
                return true;
            }

            sender.sendMessage(ChatColor.GREEN + "Pomyślnie przeładowano plugin");
            return true;
        } else sender.sendMessage(ChatColor.GRAY + "Użycie: " + ChatColor.YELLOW + "/fortune reload");
        return true;
    }
}
