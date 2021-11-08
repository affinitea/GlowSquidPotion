package me.affinity.glowpotionfin.runnables;

import me.affinity.glowpotionfin.GlowPotionFin;
import me.affinity.glowpotionfin.potions.PotionManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class TimesUpRunnable extends BukkitRunnable {

    GlowPotionFin plugin;

    public TimesUpRunnable(GlowPotionFin plugin) {
        this.plugin = plugin;
    }

    public static String doPotionHeadsUp(String potionname, ChatColor color){
        String string = ChatColor.WHITE+"Your "+color+potionname+ChatColor.WHITE+" Potion has expired!";
        String bolded = ChatColor.BOLD+string;
        return bolded;
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()){
            UUID uuid = player.getUniqueId();
            if (plugin.hasCollections.get(uuid)){
                if (plugin.timeCollections.get(uuid) <= 0){
                    plugin.hasCollections.put(uuid, false);
                }
            }
          
            }
        }
    
    public static void changeGamemode(Player player){
        player.setGameMode(PotionManager.uuidToGameMode.get(player.getUniqueId()));
    }
}
