package io.github.geniusformat.scuffedbedwars;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class GeneratorConfigManager {
    private final Scuffedbedwars plugin;
    private FileConfiguration dataConfig;
    private File configFile;

    public GeneratorConfigManager(Scuffedbedwars plugin) {
        this.plugin = plugin;
        saveDefaultConfig();
    }

    public void reloadConfig() {
        if (this.configFile == null) {
            this.configFile = new File(plugin.getDataFolder(), "generators.yml");
        }

        dataConfig = YamlConfiguration.loadConfiguration(configFile);
        InputStream defaultStream = plugin.getResource("generators.yml");
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            dataConfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getConfig() {
        if (dataConfig == null) {
            reloadConfig();
        }
        return dataConfig;
    }

    public void saveConfig() {
        if (dataConfig == null || configFile == null) {
            return;
        }
        try {
            getConfig().save(configFile);
        }
        catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not save generators.yml", e);
        }
    }

    public void saveDefaultConfig() {
        if (configFile == null) {
            configFile = new File(this.plugin.getDataFolder(), "generators.yml");
        }

        if (!(configFile.exists())) {
            plugin.saveResource("generators.yml", false);
        }
    }
}
