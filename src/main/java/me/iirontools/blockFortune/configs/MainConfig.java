package me.iirontools.blockFortune.configs;

import me.iirontools.blockFortune.BlockFortune;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainConfig {

    private final static MainConfig instance = new MainConfig();

    private File file;
    private YamlConfiguration config;

    private int fortuneMultiplierMin;
    private int fortuneMultiplierMax;

    private List<String> materialNames;
    private final List<Material> materials = new ArrayList<>();

    private List<String> toolsNames;
    private final List<Material> tools = new ArrayList<>();

    private MainConfig() {
    }


    public void load() {
        file = new File(BlockFortune.getPlugin().getDataFolder(), "config.yml");

        if (!file.exists()) {
            BlockFortune.getPlugin().saveResource("config.yml", false);
        }
        config = new YamlConfiguration();
        config.options().parseComments(true);

        try {
            config.load(file);
        } catch (Exception e) {
            BlockFortune.getPlugin().getLogger().severe("Niepoprawno załadowano konfigurację pluginu!");
        }

        fortuneMultiplierMin = config.getInt("fortune-multiplier.min");
        fortuneMultiplierMax = config.getInt("fortune-multiplier.max");

        materialNames = config.getStringList("fortune-blocks");
        toolsNames = config.getStringList("fortune-tools");

        for (String fortuneName : materialNames) {
            Material material = Material.matchMaterial(fortuneName);

            if (material != null) {
                materials.add(material);
            } else {
                BlockFortune.getPlugin().getLogger().warning("Niepoprawny materiał w konfiguracji: " + fortuneName);
            }
        }

        for (String tool : toolsNames) {
            Material material = Material.matchMaterial(tool);

            if (material != null) {
                tools.add(material);
            } else {
                BlockFortune.getPlugin().getLogger().warning("Niepoprawny materiał w konfiguracji: " + tool);
            }
        }
    }

    public Integer getFortuneMultiplierMin() {
        return fortuneMultiplierMin;
    }
    public Integer getFortuneMultiplierMax() {
        return fortuneMultiplierMax;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public List<Material> getTools() {
        return tools;
    }


    public static MainConfig getInstance () {
        return instance;
    }
}
