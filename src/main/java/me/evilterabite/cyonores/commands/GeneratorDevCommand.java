package me.evilterabite.cyonores.commands;

import me.evilterabite.cyonores.CyonOres;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class GeneratorDevCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("cleargenerators")) {
                CyonOres.activeGenerators.clear();
                sender.sendMessage("Generator list cleared.");
            }
        }
        return true;
    }
}
