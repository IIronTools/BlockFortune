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

    private int fortuneMultiplier;

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

        fortuneMultiplier = config.getInt("fortune-multiplier");
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

//    public void save() {
//        try {
//            config.save(file);
//        } catch (Exception e) {
//            BlockFortune.getPlugin().getLogger().severe("Niepoprawnie zapisano konfigurację pluginu!");
//        }
//    }

    public Integer getFortuneMultiplier() {
        return fortuneMultiplier;
    }
    public List<Material> getFortuneMaterials() {
        return fortuneMaterials;
    }

    public static MainConfig getInstance() {
        return instance;
    }


}
