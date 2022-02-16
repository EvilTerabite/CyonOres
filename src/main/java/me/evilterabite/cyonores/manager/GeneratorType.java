package me.evilterabite.cyonores.manager;

import org.bukkit.Material;

public enum GeneratorType {
    COAL,
    IRON,
    GOLD,
    DIAMOND,
    LAPIS;


    public static GeneratorType getTypeFromString(String type) {
        GeneratorType generatorType;
        switch (type) {
            case "coal" -> generatorType = GeneratorType.COAL;
            case "iron" -> generatorType = GeneratorType.IRON;
            default -> generatorType = GeneratorType.LAPIS;
        }
        return generatorType;
    }

    public static String getStringFromType(GeneratorType type) {
        String generatorType;
        switch (type) {
            case COAL -> generatorType = "coal";
            case IRON -> generatorType = "iron";
            default -> generatorType = "lapis";
        }
        return generatorType;
    }

    public static Material getMaterialFromType(GeneratorType type) {

        Material generatorType;
        switch (type) {
            case COAL -> generatorType = Material.COAL_ORE;
            case IRON -> generatorType = Material.IRON_ORE;
            default -> generatorType = Material.LAPIS_ORE;
        }
        return generatorType;
    }
}
