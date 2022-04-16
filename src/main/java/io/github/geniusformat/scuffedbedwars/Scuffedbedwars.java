package io.github.geniusformat.scuffedbedwars;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Scuffedbedwars extends JavaPlugin {
    /* Main Class */
    public String prefix = ChatColor.GOLD + "[SCUFFED BEDWARS] "; // Output prefix
    private final GeneratorManager generatorManager = new GeneratorManager(this);
    public GeneratorConfigManager generatorConfigManager;

    @Override
    public void onEnable() {
        generatorConfigManager = new GeneratorConfigManager(this);
        generatorManager.reloadGenerators();
        getServer().getConsoleSender().sendMessage(prefix + "Enjoy your scuffed bedwars! (Plugin loaded)");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(prefix + "I assume that you didn't like the experience. I'm now sad. (Plugin unloaded)");
    }

    /* Commands */
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) { sender.sendMessage(ChatColor.RED + "This command must be run as a player."); return true; }

        if (cmd.getName().equalsIgnoreCase("sbw") && sender.isOp()) {
            Player p = (Player) sender;
            if (args.length >= 2) {
                if (args[0].equalsIgnoreCase("generator")) {
                    if (args[1].equalsIgnoreCase("set")) {
                        if (args.length == 5) {
                            Material m = Material.matchMaterial(args[3]);
                            if (m == null) {
                                sender.sendMessage(ChatColor.RED + " is not a valid material.");
                                return false;
                            }
                            if (isDouble(args[4])) {
                                Block b = p.getTargetBlock(null, 3);
                                if (!(b.getType().isSolid())) {
                                    p.sendMessage(ChatColor.RED + "You must be looking at a solid block!");
                                    return false;
                                }
                                generatorManager.addGeneratorFile(args[2], b, args[3], Double.parseDouble(args[4]));
                                p.sendMessage(ChatColor.GREEN + "Created a generator for " + args[3] + " that generates items every " + args[4] + " seconds.");
                                return true;
                            }
                        }
                    }
                    else {
                        if (args.length == 3) {
                            if (args[1].equalsIgnoreCase("remove")) {
                                Block b = p.getTargetBlock(null, 3);
                                if (!(b.getType().isSolid())) {
                                    p.sendMessage(ChatColor.RED + "You must be looking at a solid block!");
                                    return false;
                                }
                                generatorManager.removeGeneratorFile(args[2], b);
                                p.sendMessage(ChatColor.GREEN + "Removed the generator.");
                                return true;
                            }
                        }
                        sender.sendMessage(ChatColor.RED + "/sbw generator [set|remove] [itemType] [seconds]");
                    }
                }
                else if (args[0].equalsIgnoreCase("reload")) {
                    reloadConfig();
                }
            }
        }

        return false;
    }

    private boolean isDouble(String text) {
        try {
            Double.parseDouble(text);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
