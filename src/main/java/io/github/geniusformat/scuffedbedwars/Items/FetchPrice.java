package io.github.geniusformat.scuffedbedwars.Items;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Collections;

public class FetchPrice {
    public static ArrayList<String> getLore(Integer price, String unit) {
        if (unit == "iron") {
            return (ArrayList<String>) Collections.singletonList(ChatColor.GRAY + price.toString() + unit);
        }
        else if (unit == "gold") {
            return (ArrayList<String>) Collections.singletonList(ChatColor.GOLD + price.toString() + unit);
        }
        else if (unit == "diamond") {
            return (ArrayList<String>) Collections.singletonList(ChatColor.AQUA + price.toString() + unit);
        }
        else if (unit == "emerald") {
            return (ArrayList<String>) Collections.singletonList(ChatColor.GREEN + price.toString() + unit);
        }
        else {
            return (ArrayList<String>) Collections.singletonList(ChatColor.RED + "Unknown Unit.");
        }
    }
}
