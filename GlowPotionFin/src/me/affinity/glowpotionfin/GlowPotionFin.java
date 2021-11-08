package me.affinity.glowpotionfin;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitTask;

import me.affinity.glowpotionfin.bstats.Metrics;
import me.affinity.glowpotionfin.listeners.EventListeners;
import me.affinity.glowpotionfin.potions.PotionManager;
import me.affinity.glowpotionfin.runnables.TimerCheckRunnable;
import me.affinity.glowpotionfin.runnables.TimesUpRunnable;
import me.affinity.glowpotionfin.utils.BrewingController;
import me.affinity.glowpotionfin.utils.BrewingRecipe;

import java.util.HashMap;
import java.util.UUID;

/**
 * Hello world!
 *
 */

public class GlowPotionFin extends JavaPlugin {

    // Storing potion boolean values
    public HashMap<UUID, Boolean> hasCollections = new HashMap<>();

    // Storing potion values in seconds
    public HashMap<UUID, Integer> timeCollections = new HashMap<>();

    private BrewingController bc;

    @Override
    public void onEnable() {
    	getCommand("glowpot").setExecutor(new PotCommands());
        // Get players currently ingame
        for (Player player : Bukkit.getOnlinePlayers()){
            UUID uuid = player.getUniqueId();
            this.hasCollections.put(uuid, false);
            this.timeCollections.put(uuid, 30);

        }



        bc = new BrewingController(this);

        // Recipe Init
        recipeInit("pot_glowsquid", PotionManager.POT_GLOWSQUID.clone(), Material.GLOW_INK_SAC);

        //Runnables  
        // Runnables
        BukkitTask TimeCheck = new TimerCheckRunnable(this).runTaskTimer(this, 20, 20);
        BukkitTask TimesUp = new TimesUpRunnable(this).runTaskTimer(this, 20, 20);
        
        // Listeners
        getServer().getPluginManager().registerEvents(new PotionListener(this), this);
        getServer().getPluginManager().registerEvents(new EventListeners(this), this);
        new Metrics(this, 10549);
    }

    @Override
    public void onDisable() {
        bc.stop();
    }


    public void recipeInit(String key, ItemStack resultstack, Material input){
        ItemStack potion = new ItemStack(Material.POTION);
        PotionMeta pm = (PotionMeta) potion.getItemMeta();
        pm.setBasePotionData(new PotionData(PotionType.AWKWARD));
        potion.setItemMeta(pm);

        BrewingRecipe recipe = new BrewingRecipe(
                new NamespacedKey(this, key),
                resultstack,
                new ItemStack(input),
                potion
        );
        bc.addRecipe(recipe);
    }
}
