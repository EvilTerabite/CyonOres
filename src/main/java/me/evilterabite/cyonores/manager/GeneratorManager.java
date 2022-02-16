package me.evilterabite.cyonores.manager;

import me.evilterabite.cyonores.CyonOres;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GeneratorManager implements Listener {

    @EventHandler
    void onGeneratorPlace(BlockPlaceEvent event) {
        Block genBlock = event.getBlock();
        ItemStack genItem = event.getItemInHand();
        ItemMeta meta = genItem.getItemMeta();
        assert meta != null;
        String displayName = meta.getDisplayName();
        if(!displayName.equalsIgnoreCase("Coal Generator")) return;
        List<Generator> activeGenerators = CyonOres.activeGenerators;
        Generator generator = new Generator(GeneratorType.getTypeFromString("coal"), genBlock.getLocation());
        activeGenerators.add(generator);
        genBlock.setType(GeneratorType.getMaterialFromType(generator.getType()));
        CyonOres.activeGenerators = activeGenerators;
        for(Generator sampleGen : CyonOres.activeGenerators) {
            Bukkit.broadcastMessage(GeneratorType.getStringFromType(sampleGen.getType()));
        }
    }

    @EventHandler
    void onOreBreak(BlockBreakEvent event) {
        Plugin plugin = CyonOres.getPlugin(CyonOres.class);
        Block block = event.getBlock();
        if(Generator.isGenerator(block)) {
            event.setCancelled(true);
            block.setType(Material.BEDROCK);
            List<ItemStack> dropItems = new ArrayList<>(event.getBlock().getDrops());
            for(ItemStack item : dropItems) {
                event.getPlayer().getInventory().addItem(item);
            }
            Generator generator = Generator.getGenerator(block);
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                assert generator != null;
                block.setType(GeneratorType.getMaterialFromType(generator.getType()));
            }, 5 * 20);
        }
    }
}
