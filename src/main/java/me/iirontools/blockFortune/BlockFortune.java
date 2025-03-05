package me.iirontools.blockFortune;

import me.iirontools.blockFortune.commands.FortuneCommand;
import me.iirontools.blockFortune.configs.MainConfig;
import me.iirontools.blockFortune.listeners.BlockBreakListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class BlockFortune extends JavaPlugin {

    private static BlockFortune plugin;

    @Override
    public void onEnable() {
        plugin = this;

        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getCommand("fortune").setExecutor(new FortuneCommand());

        MainConfig.getInstance().load();


    }

    @Override
    public void onDisable() {
    }

    public static BlockFortune getPlugin() {
        return plugin;
    }
}
