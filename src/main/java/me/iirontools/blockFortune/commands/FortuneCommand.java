package me.iirontools.blockFortune.commands;

import me.iirontools.blockFortune.BlockFortune;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class FortuneCommand implements CommandExecutor {

    private final BlockFortune plugin;

    public FortuneCommand(BlockFortune plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            try {
                plugin.getMainConfig().load();
                sender.sendMessage(Component.text("Pomyślnie przeładowano plugin.", NamedTextColor.GREEN));
            } catch (Exception e) {
                sender.sendMessage(Component.text("Wystąpił błąd w configu!", NamedTextColor.RED));
            }
        } else {
            sender.sendMessage(Component.text("Użycie: ", NamedTextColor.GRAY)
                    .append(Component.text("/fortune reload", NamedTextColor.YELLOW)));
        }
        return true;
    }
}
