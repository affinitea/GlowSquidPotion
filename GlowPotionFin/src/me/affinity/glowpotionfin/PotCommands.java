package me.affinity.glowpotionfin;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.affinity.glowpotionfin.potions.PotionManager;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class PotCommands implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length != 0 || !(sender instanceof Player) || !sender.hasPermission("giveallpots.command")) {
            return false;
        }

        Player player = (Player) sender;
        for (Map.Entry<Integer, ItemStack> entry :  player.getInventory().addItem(PotionManager.POT_GLOWSQUID.clone()).entrySet()) player.getWorld().dropItem(player.getLocation(), entry.getValue());

        return true;
    }
}
