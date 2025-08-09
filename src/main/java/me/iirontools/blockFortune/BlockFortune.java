package me.iirontools.blockFortune;

import me.iirontools.blockFortune.commands.FortuneCommand;
import me.iirontools.blockFortune.configs.MainConfig;
import me.iirontools.blockFortune.listeners.BlockBreakListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class BlockFortune extends JavaPlugin {

    private MainConfig mainConfig;

    @Override
    public void onEnable() {
        this.mainConfig = new MainConfig(this);

        getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);
        getCommand("fortune").setExecutor(new FortuneCommand(this));
    }

    public MainConfig getMainConfig() {
        return mainConfig;
    }
}
