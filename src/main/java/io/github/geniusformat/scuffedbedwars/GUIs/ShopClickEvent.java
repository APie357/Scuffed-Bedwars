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
import java.util.Objects;

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

        if (Objects.equals(unit, "iron")) {
            unitMat = Material.matchMaterial(unit + "_ingot");
        } else if (Objects.equals(unit, "gold")) {
            unitMat = Material.matchMaterial(unit + "_ingot");
        } else if (Objects.equals(unit, "diamond") || Objects.equals(unit, "diamonds")) {
            unitMat = Material.matchMaterial(unit);
        } else if (Objects.equals(unit, "emerald") || Objects.equals(unit, "emeralds")) {
            unitMat = Material.matchMaterial(unit);
        } else {
            unitMat = Material.BARRIER;
        }

        assert unitMat != null;
        ItemStack req = new ItemStack(unitMat, price);

        if (p.getInventory().containsAtLeast(req, price)) {
            p.getInventory().remove(req);
            ItemStack itemNoLore = i;
            ItemMeta itemNoLoreMeta = itemNoLore.getItemMeta();
            itemNoLoreMeta.setLore(null);
            itemNoLore.setItemMeta(itemNoLoreMeta);
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 3.0f, 0.5f);
            p.getInventory().addItem(itemNoLore);
            Shop shop = new Shop("lime");
            p.openInventory(shop.getInventory());
        } else {
            p.sendMessage(ChatColor.RED + "You don't have the dough...");
        }
    }
}
