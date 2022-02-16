package me.evilterabite.cyonores;

import me.evilterabite.cyonores.commands.GeneratorDevCommand;
import me.evilterabite.cyonores.commands.GetGeneratorCommand;
import me.evilterabite.cyonores.manager.Generator;
import me.evilterabite.cyonores.manager.GeneratorManager;
import me.evilterabite.cyonores.manager.GeneratorType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class CyonOres extends JavaPlugin {

    public static List<Generator> activeGenerators;

    @Override
    public void onEnable() {
        activeGenerators = new ArrayList<>();
        setupGenerators();
        saveDefaultConfig();

        //Events
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new GeneratorManager(), this);

        //commands
        getCommand("getgenerator").setExecutor(new GetGeneratorCommand());
        getCommand("gendev").setExecutor(new GeneratorDevCommand());
    }

    @Override
    public void onDisable() {
        disableGenerators();
    }


    public void setupGenerators() {
        List<String> genIDList = getConfig().getStringList("generators");
        for(String s : genIDList) {
            String type = getConfig().getString("generators.{i}.type".replace("{i}", s));
            String configLoc = "generators.{i}.location".replace("{i}", s);
            Location location = new Location(
                    Bukkit.getWorld(Objects.requireNonNull(getConfig().getString(configLoc + ".world")))
                    ,getConfig().getInt(configLoc + ".x")
                    ,getConfig().getInt(configLoc + ".y")
                    ,getConfig().getInt(configLoc + ".z")
            );

            assert type != null;
            activeGenerators.add(new Generator(GeneratorType.getTypeFromString(type), location));
        }
        saveConfig();
    }

    public void disableGenerators() {
        int i = 0;

        for(Generator generator : activeGenerators) {
            String type = GeneratorType.getStringFromType(generator.getType());
            Location location = generator.getLocation();
            String world = Objects.requireNonNull(location.getWorld()).getName();
            double x = location.getX();
            double y = location.getY();
            double z = location.getZ();

            getConfig().set("generator.{i}.type".replace("{i}", Integer.toString(i)), type);
            getConfig().set("generator.{i}.location.world".replace("{i}", Integer.toString(i)), world);
            getConfig().set("generator.{i}.location.x".replace("{i}", Integer.toString(i)), x);
            getConfig().set("generator.{i}.location.y".replace("{i}", Integer.toString(i)), y);
            getConfig().set("generator.{i}.location.z".replace("{i}", Integer.toString(i)), z);

            i++;
        }
        saveConfig();
    }
}
