package me.evilterabite.cyonores.manager;

import me.evilterabite.cyonores.CyonOres;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.List;

public class Generator {

    private GeneratorType type;
    private Location location;

    public Generator(GeneratorType type, Location location) {
        this.type = type;
        this.location = location;
    }

    public GeneratorType getType() {
        return type;
    }

    public void setType(GeneratorType type) {
        this.type = type;
    }

    public Location getLocation() {
        return location;
    }

    public static boolean isGenerator(Block block) {
        List<Generator> activeGenerators = CyonOres.activeGenerators;
        for(Generator generator : activeGenerators) {
            Location location = generator.getLocation();
            if(block.getLocation().equals(location)) {
                return true;
            }
        }
        return false;
    }

    public static Generator getGenerator(Block block) {
        List<Generator> activeGenerators = CyonOres.activeGenerators;
        for(Generator generator : activeGenerators) {
            Location location = generator.getLocation();
            if(block.getLocation().equals(location)) {
                return generator;
            }
        }
        return null;
    }
}
