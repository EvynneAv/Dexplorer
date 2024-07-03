package com.example.dexplorer.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class PokemonStat implements Parcelable {
    private String name;
    private String url;

    protected PokemonStat(Parcel in) {
        name = in.readString();
        url = in.readString();
    }

    public static final Creator<PokemonStat> CREATOR = new Creator<PokemonStat>() {
        @Override
        public PokemonStat createFromParcel(Parcel in) {
            return new PokemonStat(in);
        }

        @Override
        public PokemonStat[] newArray(int size) {
            return new PokemonStat[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PokemonStat(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(url);
    }
}
