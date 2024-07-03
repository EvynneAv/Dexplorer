package com.example.dexplorer.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.sql.Types;
import java.util.List;
import java.util.Map;

public class Pokemom implements Parcelable{


    private int id = 0;
    private String name;

    private int height = 0;
    private int weight = 0;

    @SerializedName("types")
    private List<PokemonType> types;

    @SerializedName("sprites")
    private Sprites sprites;
    @SerializedName("abilities")
    private List<AbilitySlot> abilities;

    @SerializedName("stats")
    private  List<StatSlot> stats;



    public Pokemom(String name) {
        this.name = name;
    }

    public Pokemom() {
    }


    protected Pokemom(Parcel in) {
        id = in.readInt();
        name = in.readString();
        height = in.readInt();
        weight = in.readInt();
        types = in.createTypedArrayList(PokemonType.CREATOR);
        sprites = in.readParcelable(Sprites.class.getClassLoader());
        abilities= in.createTypedArrayList(AbilitySlot.CREATOR);
        stats = in.createTypedArrayList(StatSlot.CREATOR);
    }

    public static final Creator<Pokemom> CREATOR = new Creator<Pokemom>() {
        @Override
        public Pokemom createFromParcel(Parcel in) {
            return new Pokemom(in);
        }

        @Override
        public Pokemom[] newArray(int size) {
            return new Pokemom[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }


    public List<PokemonType> getTypes() {
        return types;
    }

    public void setTypes(List<PokemonType> types) {
        this.types = types;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<AbilitySlot> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<AbilitySlot> abilities) {
        this.abilities = abilities;
    }

    public List<StatSlot> getStats() {
        return stats;
    }

    public void setStats(List<StatSlot> stats) {
        this.stats = stats;
    }


    public void setDetails(int id, Sprites sprites, List<PokemonType> types, int height, int weight, List<AbilitySlot> abilities, List<StatSlot> stats) {
        setId(id);
        setSprites(sprites);
        setTypes(types);
        setHeight(height);
        setWeight(weight);
        setAbilities(abilities);
        setStats(stats);

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(height);
        dest.writeInt(weight);
        dest.writeTypedList(types);
        dest.writeParcelable(sprites, flags);
        dest.writeTypedList(abilities);
        dest.writeTypedList(stats);
    }
}




