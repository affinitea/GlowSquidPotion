package me.affinity.glowpotionfin.runnables;



import me.affinity.glowpotionfin.GlowPotionFin;
import me.affinity.glowpotionfin.utils.SendTitleBarMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.ChatColor;


import java.util.UUID;

public class TimerCheckRunnable extends BukkitRunnable {

    GlowPotionFin plugin;

    public TimerCheckRunnable(GlowPotionFin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()){
            UUID uuid = player.getUniqueId();

            if (plugin.hasCollections.get(uuid)){
                int collectionstimer = plugin.timeCollections.get(uuid)-1;
                SendTitleBarMessage.sendMessage(player, ChatColor.GREEN + "Glow Squid"  + ChatColor.AQUA + " Potion", collectionstimer);
                plugin.timeCollections.put(uuid, collectionstimer);
            }
        }
    }
}
            