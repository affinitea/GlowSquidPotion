package me.affinity.glowpotionfin;


import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.entity.Player;
import me.affinity.glowpotionfin.potions.PotionManager;

import java.util.UUID;

public class PotionListener implements Listener {

    GlowPotionFin plugin;

    public PotionListener(GlowPotionFin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDrink(PlayerItemConsumeEvent e) {
        Player player = e.getPlayer();
        PlayerInventory pi = player.getInventory();
        UUID uuid = player.getUniqueId();
        
        if (e.getItem().isSimilar(PotionManager.POT_GLOWSQUID)){
            plugin.hasCollections.put(uuid, true);
            plugin.timeCollections.put(uuid, 30);



        if (e.getItem().getType().equals(Material.MILK_BUCKET)){
            plugin.hasCollections.put(uuid, false);

            // Integers
            plugin.timeCollections.put(uuid, 0);
            pi.setHelmet(null);
        }

    }
    }}
