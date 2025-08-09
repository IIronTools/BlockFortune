package me.iirontools.blockFortune.configs;

import me.iirontools.blockFortune.BlockFortune;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainConfig {

    private final BlockFortune plugin;
    private File file;
    private YamlConfiguration config;
    private int fortuneMultiplierMin;
    private int fortuneMultiplierMax;
    private final List<Material> materials = new ArrayList<>();
    private final List<Material> tools = new ArrayList<>();

    public MainConfig(BlockFortune plugin) {
        this.plugin = plugin;
        load();
    }

    public void load() {
        file = new File(plugin.getDataFolder(), "config.yml");
        if (!file.exists()) plugin.saveResource("config.yml", false);

        config = YamlConfiguration.loadConfiguration(file);
        config.options().parseComments(true);

        fortuneMultiplierMin = config.getInt("fortune-multiplier.min");
        fortuneMultiplierMax = config.getInt("fortune-multiplier.max");

        for (String name : config.getStringList("fortune-blocks")) {
            Material material = Material.matchMaterial(name);
            if (material != null) materials.add(material);
            else plugin.getLogger().warning("Niepoprawny materiał w konfiguracji: " + name);
        }

        for (String name : config.getStringList("fortune-tools")) {
            Material material = Material.matchMaterial(name);
            if (material != null) tools.add(material);
            else plugin.getLogger().warning("Niepoprawny materiał w konfiguracji: " + name);
        }
    }

    public int getFortuneMultiplierMin() {
        return fortuneMultiplierMin;
    }

    public int getFortuneMultiplierMax() {
        return fortuneMultiplierMax;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public List<Material> getTools() {
        return tools;
    }
}
