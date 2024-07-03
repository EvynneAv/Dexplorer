package com.example.dexplorer.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class StatSlot implements Parcelable {
    private int base_stat = 0;
    private int effort = 0;
    private PokemonStat stat;

    public StatSlot(int base_stat) {
        this.base_stat = base_stat;
    }

    protected StatSlot(Parcel in) {
        base_stat = in.readInt();
        effort = in.readInt();
        stat = in.readParcelable(PokemonStat.class.getClassLoader());
    }

    public static final Creator<StatSlot> CREATOR = new Creator<StatSlot>() {
        @Override
        public StatSlot createFromParcel(Parcel in) {
            return new StatSlot(in);
        }

        @Override
        public StatSlot[] newArray(int size) {
            return new StatSlot[size];
        }
    };

    public int getBase_stat() {
        return base_stat;
    }

    public void setBase_stat(int base_stat) {
        this.base_stat = base_stat;
    }

    public int getEffort() {
        return effort;
    }

    public void setEffort(int effort) {
        this.effort = effort;
    }

    public PokemonStat getStat() {
        return stat;
    }

    public void setStat(PokemonStat stat) {
        this.stat = stat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(base_stat);
        dest.writeInt(effort);
        dest.writeParcelable(stat, flags);
    }
}
