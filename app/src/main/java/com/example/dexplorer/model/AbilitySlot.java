package com.example.dexplorer.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class AbilitySlot implements Parcelable {
    @SerializedName("ability")
    private PokemonAbility ability;
    @SerializedName("is_hidden")
    private boolean isHidden;
    @SerializedName("slot")
    private int slot;

    public AbilitySlot(PokemonAbility ability, boolean isHidden, int slot) {
        this.ability = ability;
        this.isHidden = isHidden;
        this.slot = slot;
    }


    protected AbilitySlot(Parcel in) {
        ability = in.readParcelable(PokemonAbility.class.getClassLoader());
        isHidden = in.readByte() != 0;
        slot = in.readInt();
    }

    public static final Creator<AbilitySlot> CREATOR = new Creator<AbilitySlot>() {
        @Override
        public AbilitySlot createFromParcel(Parcel in) {
            return new AbilitySlot(in);
        }

        @Override
        public AbilitySlot[] newArray(int size) {
            return new AbilitySlot[size];
        }
    };

    public PokemonAbility getAbility() {
        return ability;
    }

    public void setAbility(PokemonAbility ability) {
        this.ability = ability;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeParcelable(ability, flags);
        dest.writeByte((byte) (isHidden ? 1 : 0));
        dest.writeInt(slot);
    }
}
