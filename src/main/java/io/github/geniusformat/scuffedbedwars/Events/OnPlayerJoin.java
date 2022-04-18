package io.github.geniusformat.scuffedbedwars.Events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;

public class OnPlayerJoin implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        createScoreboard(e.getPlayer());

        if (!(Bukkit.getOnlinePlayers().isEmpty())) {
            for (Player o : Bukkit.getOnlinePlayers()) {
                createScoreboard(o);
            }
        }
    }

    public void createScoreboard(Player p) {
        ScoreboardManager mgr = Bukkit.getScoreboardManager();
        Scoreboard sb = mgr.getNewScoreboard();
        Objective obj = sb.registerNewObjective("SBW-SB1", "dummy", ChatColor.GOLD + "Scuffed Bedwars");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score scoreWarn = obj.getScore(ChatColor.RED + "IN DEVELOPMENT");
        scoreWarn.setScore(10);

        Score score1 = obj.getScore(ChatColor.RED + "Red");
        score1.setScore(9);
        Score score2 = obj.getScore(ChatColor.GOLD + "Orange");
        score2.setScore(8);
        Score score3 = obj.getScore(ChatColor.YELLOW + "Yellow");
        score3.setScore(7);
        Score score4 = obj.getScore(ChatColor.GREEN + "Green");
        score4.setScore(6);
        Score score5 = obj.getScore(ChatColor.BLUE + "Blue");
        score5.setScore(5);
        Score score6 = obj.getScore(ChatColor.DARK_PURPLE + "Purple");
        score6.setScore(4);
        Score score7 = obj.getScore(ChatColor.LIGHT_PURPLE + "Pink");
        score7.setScore(3);
        Score score8 = obj.getScore(ChatColor.GRAY + "Grey");
        score8.setScore(2);

        Score scoreSeperator = obj.getScore(ChatColor.GRAY + "-=-=-=-=-=-=-=-=-=-");
        scoreSeperator.setScore(1);
        Score scoreIPAddr = obj.getScore(ChatColor.GOLD + "play.scuffedmc.tk");
        scoreIPAddr.setScore(0);
    }
}
