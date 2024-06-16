package com.example.dexplorer.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Pokemom implements Parcelable {
    private int id = 0;
    private String name;
    private String type;

    @SerializedName("sprites")
    private Sprites sprites;



    public Pokemom(String name) {
        this.name = name;
    }
    public Pokemom(){}

    protected Pokemom(Parcel in) {
        id = in.readInt();
        name = in.readString();
        type = in.readString();
        sprites = in.readParcelable(Sprites.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(type);
        dest.writeParcelable(sprites, flags);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
//    public void setId(int id) { this.id = String.valueOf(id); }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public Sprites getSprites() {

        Log.d("Dentro depokemon","Rodou o get sprites");
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }



    public void setDetails(int id, Sprites sprites /*String type*/){
        setId(id);
        setSprites(sprites);

//        setType(type);

    }


}
