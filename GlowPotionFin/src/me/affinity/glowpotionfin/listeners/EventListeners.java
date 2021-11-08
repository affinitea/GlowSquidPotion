package me.affinity.glowpotionfin.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.GlowSquid;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffectType;

import io.netty.util.internal.ThreadLocalRandom;
import me.affinity.glowpotionfin.GlowPotionFin;

import java.util.UUID;

public class EventListeners implements Listener {

    GlowPotionFin plugin;

    public EventListeners(GlowPotionFin plugin) {
        this.plugin = plugin;
    }
    
    
    //spawning glow squids
	@SuppressWarnings("deprecation")
	@EventHandler
    public void invisBat(CreatureSpawnEvent v) {
    	GlowSquid gs = (GlowSquid) v.getEntity();

    	if(gs.getLastDamage() >= 0) {
    		gs.resetMaxHealth();
    	}
    }
    
    @EventHandler
    public void itemAndSFX(PlayerItemConsumeEvent ce) {
    	Player p = ce.getPlayer();
    	Location loc = p.getLocation();
    	Location eyes = p.getEyeLocation();
    	
    	if(ce.getItem().getType().name().toLowerCase().contains("potion")) {
            //play sound and give "sea" visual effect
            p.playSound(loc, Sound.MUSIC_DISC_CAT, 2, 1);
            p.getInventory().setHelmet(new ItemStack(Material.CARVED_PUMPKIN));
            
            p.getPotionEffect(PotionEffectType.WATER_BREATHING);
            
            
    	}
    }
    

    //this is where the effects for the "collection" potion are stored - change this to desired effects
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        //define main variables
        Player p = e.getPlayer();
        PlayerInventory pi = p.getInventory();
        ItemStack sea = new ItemStack(Material.CARVED_PUMPKIN);
        Location loc = p.getLocation();
        Location eyes = p.getEyeLocation();
        Block block = loc.getBlock().getRelative(BlockFace.DOWN);
        
        //number and boolean variables
        int tlr = (int) ThreadLocalRandom.current().nextInt(1, 25);
        boolean iw = p.isInWater();
        
        //actual thing
        if (plugin.hasCollections.get(e.getPlayer().getUniqueId())){
           
              //send chat messages and give potion effect while in water
              if (block.getType() == Material.WATER) {
                  p.sendMessage(ChatColor.GREEN + "GLOW SQUID RAVE?");
                  p.sendMessage(ChatColor.AQUA + "GLOW SQUID RAVE!");
              
                  //while player is actively in water, 4 glow squids will spawn in random areas on invisible bats
                	  e.getPlayer().getWorld().spawnEntity(loc, EntityType.GLOW_SQUID);
                	  e.getPlayer().getWorld().spawnEntity(loc, EntityType.GLOW_SQUID);
                  
              }
       }
        
        else  {
        	p.getInventory().setHelmet(null);
        }
   }
    
    

    	
/* og code
 *         if (plugin.hasCollections.get(e.getPlayer().getUniqueId())){
            if (e.getBlock().getType().equals(Material.CHEST)){
                Block block = e.getBlock();
                Chest chestblock = (Chest) block.getState();
                Inventory inventory = chestblock.getBlockInventory();
                for (int i=0;i<inventory.getSize();i++){
                    int percentage = (int) (Math.random()*100);
                    if (percentage < 40){
                        int stack = (int) (Math.random() * 10);
                        Random random = new Random();
                        List<Material> materials = Arrays.asList(Material.values());
                        Material randommaterial = materials.get(random.nextInt(materials.size()));
                        ItemStack itemStack = new ItemStack(randommaterial, stack);
                        inventory.setItem(i, itemStack);
                    }
                }
            }
        }
 */
    

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        UUID uuid = e.getPlayer().getUniqueId();
        // Boolean values
        plugin.hasCollections.put(uuid, false);

        // Integers
        plugin.timeCollections.put(uuid, 0);
    }
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e){
        UUID uuid = e.getPlayer().getUniqueId();
        // Boolean values
        plugin.hasCollections.put(uuid, false);
        e.getPlayer().setAllowFlight(false);

        // Integers
        plugin.timeCollections.put(uuid, 0);
    }
}
