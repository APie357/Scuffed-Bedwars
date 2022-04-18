package io.github.geniusformat.scuffedbedwars.GUIs;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ShopClickEvent implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getClickedInventory() == null) { return; }
        if (e.getClickedInventory().getHolder() instanceof Shop) {
            e.setCancelled(true);
            Player p = (Player) e.getWhoClicked();

            if (e.getCurrentItem() == null) { return; }
            if (e.getCurrentItem().getType() != Material.AIR) {
                buyItem(p, e.getCurrentItem());
            }
        }
    }

    public void buyItem(Player p, ItemStack i) {
        ArrayList<String> lore = (ArrayList<String>) i.getItemMeta().getLore();
        String[] loreParts = lore.get(0).split(" ");
        int price = Integer.parseInt(ChatColor.stripColor(loreParts[0]));
        String unit = ChatColor.stripColor(loreParts[1]);
        Material unitMat = null;

        if (unit == "iron") { unitMat = Material.matchMaterial(unit + "_ingot"); }
        else if (unit == "gold") { unitMat = Material.matchMaterial(unit + "_ingot"); }
        else if (unit == "diamond" || unit == "diamonds") { unitMat = Material.matchMaterial(unit); }
        else if (unit == "emerald" || unit == "emeralds") { unitMat = Material.matchMaterial(unit); }
        else { unitMat = Material.BARRIER; }

        assert unitMat != null;
        ItemStack req = new ItemStack(unitMat, price);

        if (p.getInventory().containsAtLeast(req, price)) {
            p.getInventory().remove(req);
            ItemStack itemNoLore = i;
            ItemMeta itemNoLoreMeta = itemNoLore.getItemMeta();
            itemNoLoreMeta.setLore(null);
            itemNoLore.setItemMeta(itemNoLoreMeta);
            p.getInventory().addItem(itemNoLore);
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 3.0f, 0.5f);
        }
        else {
            p.sendMessage(ChatColor.RED + "You don't have the dough...");
        }

        p.getInventory().addItem(debugItem(price, unit, unitMat.toString(), loreParts));
    }

    private ItemStack debugItem(Integer price, String unit, String mat, String[] lP) {
        ItemStack i = new ItemStack(Material.BARRIER, 1);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(ChatColor.RED + "DEBUG ITEM");
        ArrayList<String> l = new ArrayList<>();
        l.add("Price: " + price.toString() + " : " + lP[0]);
        l.add("Unit: " + unit + " : " + lP[1] + " : " + mat);
        m.setLore(l);
        i.setItemMeta(m);
        return i;
    }
}
