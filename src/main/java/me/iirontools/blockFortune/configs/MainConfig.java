package me.iirontools.blockFortune.configs;

import me.iirontools.blockFortune.BlockFortune;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainConfig {

    private final static MainConfig instance = new MainConfig();

    private File file;
    private YamlConfiguration config;

    private int fortuneMultiplierMin;
    private int fortuneMultiplierMax;

    private List<String> fortuneNames;
    private List<Material> fortuneMaterials = new ArrayList<>();

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

        fortuneNames = config.getStringList("fortune-blocks");

        for (String fortuneName : fortuneNames) {
            Material material = Material.matchMaterial(fortuneName);

            if (material != null) {
                fortuneMaterials.add(material);
            } else {
                BlockFortune.getPlugin().getLogger().warning("Niepoprawny materiał w konfiguracji: " + fortuneName);
            }
        }
    }

    public Integer getFortuneMultiplierMin() {
        return fortuneMultiplierMin;
    }
    public Integer getFortuneMultiplierMax() {
        return fortuneMultiplierMax;
    }

    public List<Material> getFortuneMaterials () {
        return fortuneMaterials;
    }

    public static MainConfig getInstance () {
        return instance;
    }
}
