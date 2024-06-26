package com.example.dexplorer.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class PokemonType implements Parcelable{
    @SerializedName("slot")
    private int slot;
    @SerializedName("type")
    private PokemonTypeDetail type;

    protected PokemonType(Parcel in) {


        slot = in.readInt();
        type = in.readParcelable(PokemonTypeDetail.class.getClassLoader());
    }


    public static final Creator<PokemonType> CREATOR = new Creator<PokemonType>() {
        @Override
        public PokemonType createFromParcel(Parcel in) {
            return new PokemonType(in);
        }

        @Override
        public PokemonType[] newArray(int size) {
            return new PokemonType[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(slot);
        dest.writeParcelable(type, flags);
    }
}
