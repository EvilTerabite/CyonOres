package me.evilterabite.cyonores.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GetGeneratorCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 1) {
                String cmd = args[0];
                if(cmd.equals("coal")) {
                    ItemStack gen = new ItemStack(Material.END_STONE);
                    ItemMeta meta = gen.getItemMeta();
                    assert meta != null;
                    meta.setDisplayName("Coal Generator");
                    gen.setItemMeta(meta);

                    player.getInventory().addItem(gen);
                }
            }
        }

        return true;
    }
}
