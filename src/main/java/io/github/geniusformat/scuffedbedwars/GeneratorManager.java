package io.github.geniusformat.scuffedbedwars;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

public class GeneratorManager {
    // TODO: Make generators save in a config file
    // TODO: [IN PROGRESS] Make generators load on startup from config file

    private Map<Location, Generator> gens = new HashMap();
    private Plugin plugin;

    public GeneratorManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void addGenerator(Block b, ItemStack item, double time) {
        this.gens.put(b.getLocation(), new Generator(b, item, time));
        this.gens.get(b.getLocation()).runTaskTimer(this.plugin, 0L, 1L);
    }

    public void removeGenerator(Block b) {
        if (this.gens.containsKey(b.getLocation())) {
            this.gens.get(b.getLocation()).cancel();
            this.gens.remove(b);
        }
    }

    public void reloadGenerators() {
        for (Map.Entry<Location, Generator> gen : gens.entrySet()) {
            removeGenerator((Block) gen.getKey());
        }
    }
}
