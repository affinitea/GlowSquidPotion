package me.affinity.glowpotionfin.potions;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import me.affinity.glowpotionfin.utils.PotionItemBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PotionManager extends ItemStack {

    static String prefix = ChatColor.WHITE+"Potion of ";
    public static Map<UUID, GameMode> uuidToGameMode = new HashMap<>();
    public static final ItemStack POT_GLOWSQUID = PotionItemBuilder.of(Material.POTION)
            .name(prefix+ChatColor.DARK_BLUE+"Glow Squid"+ChatColor.GRAY+" [2:00]")
            .lore(ChatColor.GRAY+"It's a (legal) Glow Squid Party")
            //the enchantment needs to be changed to a referenced effects aka all the things
            .enchantment(Enchantment.CHANNELING, 1)
            .flags(ItemFlag.HIDE_ENCHANTS)
            .flags(ItemFlag.HIDE_POTION_EFFECTS)
            .color(Color.BLUE)
            .build();
 
    
    public static void run(ItemStack stack) {

    }
}
