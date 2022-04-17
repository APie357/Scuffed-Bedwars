package io.github.geniusformat.scuffedbedwars.GUIs;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ShopClickEvent implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getClickedInventory() == null) { return; }
        if (e.getClickedInventory().getHolder() instanceof Shop) {
            e.setCancelled(true);
            Player p = (Player) e.getWhoClicked();
        }
    }
}
