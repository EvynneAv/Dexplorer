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

    @SerializedName("types")
    private List<PokemonType> types;

    @SerializedName("sprites")
    private Sprites sprites;


    public Pokemom(String name) {
        this.name = name;
    }

    public Pokemom() {
    }

    protected Pokemom(Parcel in) {
        id = in.readInt();
        name = in.readString();
        types = in.createTypedArrayList(PokemonType.CREATOR);
        sprites = in.readParcelable(Sprites.class.getClassLoader());

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


    public void setDetails(int id, Sprites sprites, List<PokemonType> types) {
        setId(id);
        setSprites(sprites);
        setTypes(types);


    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeTypedList(types);
        dest.writeParcelable(sprites, flags);
    }
}




