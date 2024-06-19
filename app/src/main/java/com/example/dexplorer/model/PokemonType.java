package com.example.dexplorer.model;

import com.google.gson.annotations.SerializedName;

public class PokemonType {
    @SerializedName("slot")
    private int slot;
    @SerializedName("type")
    private PokemonTypeDetail type;

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public PokemonTypeDetail getType() {
        return type;
    }

    public void setType(PokemonTypeDetail type) {
        this.type = type;
    }

    public PokemonType(int slot, PokemonTypeDetail type) {
        this.slot = slot;
        this.type= type;
    }
}
