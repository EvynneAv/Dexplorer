package com.example.dexplorer.utils;

import java.util.HashMap;
import java.util.Map;

public class PokemonUtils {
    public static final Map<String, String> TYPE_ICON_MAP = new HashMap<>();

    static {
        TYPE_ICON_MAP.put("grass", "grass.png");
        TYPE_ICON_MAP.put("bug", "bug.png");
        TYPE_ICON_MAP.put("dark", "dark.png");
        TYPE_ICON_MAP.put("dragon", "dragon.png");
        TYPE_ICON_MAP.put("electric", "electric.png");
        TYPE_ICON_MAP.put("fairy", "fairy.png");
        TYPE_ICON_MAP.put("fighting", "fighting.png");
        TYPE_ICON_MAP.put("fire", "fire.png");
        TYPE_ICON_MAP.put("flying", "flying.png");
        TYPE_ICON_MAP.put("ghost", "ghost.png");
        TYPE_ICON_MAP.put("ground", "ground.png");
        TYPE_ICON_MAP.put("ice", "ice.png");
        TYPE_ICON_MAP.put("normal", "normal.png");
        TYPE_ICON_MAP.put("poison", "poison.png");
        TYPE_ICON_MAP.put("psychic", "psychic.png");
        TYPE_ICON_MAP.put("rock", "rock.png");
        TYPE_ICON_MAP.put("steel", "steel.png");
        TYPE_ICON_MAP.put("stellar", "stellar.png");
        TYPE_ICON_MAP.put("unknown", "unknown.png");
        TYPE_ICON_MAP.put("water", "water.png");
    }

    public static String getIconFilenameForTypeName(String typeName) {
        return TYPE_ICON_MAP.get(typeName);
    }
}


