package io.github.geniusformat.scuffedbedwars.GUIs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shop implements InventoryHolder {
    private Inventory inv;

    public Shop(String team) {
        inv = Bukkit.createInventory(this, 54, ChatColor.GOLD + "Scuffed Shop");
        init(team);
    }

    private void init(String team) {
        inv.setItem(0, newItemFromString("lime_wool", null, 16, 4, "iron"));
        inv.setItem(1, newItemFromString("lime_terracotta", null, 12, 16, "iron"));
        inv.setItem(2, newItem(Material.OAK_PLANKS, null, 16, 4, "gold"));
        inv.setItem(3, newItem(Material.END_STONE, null, 12, 24, "iron"));
        inv.setItem(4, newItem(Material.LADDER, null, 8, 4, "iron"));
        inv.setItem(5, newItem(Material.OBSIDIAN, null, 4, 4, "emerald"));
        inv.setItem(6, newItem(Material.STONE_SWORD, null, 1, 10, "iron"));
        inv.setItem(7, newItem(Material.IRON_SWORD, null, 1, 7, "gold"));
        inv.setItem(8, newItem(Material.DIAMOND_SWORD, null, 1, 4, "emerald"));
    }

    private ItemStack newItem(Material m, @Nullable String name, @Nullable Integer amount, @Nullable Integer price, @Nullable String unit) {
        if (amount == null) { amount = 1; }
        if (m == null) { return itemError("Item material is null.", "newItem()", "io.github.geniusformat.scuffedbedwars.GUIs.Shop"); }
        ItemStack item = new ItemStack(m, amount);
        if (item == null) { return itemError("Item is null.", "newItem()", "io.github.geniusformat.scuffedbedwars.GUIs.Shop"); }
        ItemMeta meta = item.getItemMeta();

        if (name != null) { meta.setDisplayName(name); }
        if (price != null && unit != null) {
            ChatColor priceColor = null;

            switch (unit) {
                case "iron":
                    priceColor = ChatColor.GRAY;
                    break;
                case "gold":
                    priceColor = ChatColor.GOLD;
                    break;
                case "diamond":
                    priceColor = ChatColor.AQUA;
                    break;
                case "emerald":
                    priceColor = ChatColor.GREEN;
                    break;
            }

            List<String> lore = Collections.singletonList(priceColor + price.toString() + " " + unit);

            meta.setLore(lore);
        }

        item.setItemMeta(meta);
        return item;
    }

    private ItemStack itemError(String e, String func, String cls) {
        ItemStack item = new ItemStack(Material.BARRIER, 1);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();

        meta.setDisplayName(ChatColor.RED + "An error has occurred whilst trying to create this item.");
        lore.add(ChatColor.GRAY + "DEBUG INFO:");
        lore.add(ChatColor.GRAY + "PROBLEM: " + e);
        lore.add(ChatColor.GRAY + "FUNCTION: " + func);
        lore.add(ChatColor.GRAY + "CLASS: " + cls);

        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack newItemFromString(String m, @Nullable String name, @Nullable Integer amount, @Nullable Integer price, @Nullable String unit) {
        Material mI = Material.matchMaterial(m);
        if (mI == null) { return itemError("Item is null.", "newItemFromString()", "io.github.geniusformat.scuffedbedwars.GUIs.Shop"); }
        return newItem(mI, name, amount, price, unit);
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
