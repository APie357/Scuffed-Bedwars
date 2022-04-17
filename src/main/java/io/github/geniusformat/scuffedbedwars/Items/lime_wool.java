package io.github.geniusformat.scuffedbedwars.Items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class lime_wool {
    private ItemStack i = new ItemStack(Material.LIME_WOOL, 16);

    public String getPriceUnit() { return "iron"; }
    public Integer getPrice() { return 4; }
    public ItemStack getInventoryItem() {
        return i;
    }
    public ItemStack getShopItem() {
        ItemStack i = this.i;
        ItemMeta m = i.getItemMeta();
        m.setLore(FetchPrice.getLore(getPrice(), getPriceUnit()));
        i.setItemMeta(m);
        return i;
    }
}
