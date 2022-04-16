package io.github.geniusformat.scuffedbedwars;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GeneratorManager {
    // TODO: Make generators save in a config file
    // TODO: [IN PROGRESS] Make generators load on startup from config file

    private Map<Location, Generator> gens = new HashMap();
    private Plugin plugin;
    private GeneratorConfigManager generatorConfigManager;

    public GeneratorManager(Plugin plugin) {
        this.plugin = plugin;
        generatorConfigManager = new GeneratorConfigManager((Scuffedbedwars) plugin);
    }

    public void addGenerator(Block b, ItemStack item, double time) {
        this.gens.put(b.getLocation(), new Generator(b, item, time));
        this.gens.get(b.getLocation()).runTaskTimer(this.plugin, 0L, 1L);
    }

    public void addGeneratorFile(String name, Block b, String itemName, double time) {
        addGenerator(b, new ItemStack(Material.matchMaterial(itemName)), time);
        Location location = b.getLocation();
        generatorConfigManager.getConfig().set(name + ".location", location.getBlockX() + "/" + location.getBlockY() + "/" + location.getBlockZ() + "/" + location.getWorld().getName());
        generatorConfigManager.getConfig().set(name + ".item", itemName);
        generatorConfigManager.getConfig().set(name + ".delay", time);
        generatorConfigManager.saveConfig();
    }

    public void removeGenerator(Block b) {
        if (this.gens.containsKey(b.getLocation())) {
            this.gens.get(b.getLocation()).cancel();
            this.gens.remove(b);
        }
    }

    public void removeGeneratorFile(String name, Block b) {
        if (generatorConfigManager.getConfig().contains(name)) {
            generatorConfigManager.getConfig().set(name, null);
            generatorConfigManager.saveConfig();
            removeGenerator(b);
        }
    }

    public void reloadGenerators() {
        for (Map.Entry<Location, Generator> gen : gens.entrySet()) {
            removeGenerator((Block) gen.getKey());
        }
        for (String key : generatorConfigManager.getConfig().getKeys(false)) {
            String[] parts = Objects.requireNonNull(generatorConfigManager.getConfig().getString(key + ".location")).split("/");
            ItemStack item = new ItemStack(Objects.requireNonNull(Material.matchMaterial(Objects.requireNonNull(generatorConfigManager.getConfig().getString(key + ".item")))), 1);
            Location l = new Location(Bukkit.getServer().getWorld(parts[3]), Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
            addGenerator(l.getBlock(), item, generatorConfigManager.getConfig().getDouble(key + ".delay"));
        }
    }
}
