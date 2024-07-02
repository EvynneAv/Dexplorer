package com.example.dexplorer.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class PokemonAbility implements Parcelable {
    @SerializedName("name")
    private String name;
    @SerializedName("url")
    private String url;

    public PokemonAbility(String name, String url) {
        this.name = name;
        this.url = url;
    }

    protected PokemonAbility(Parcel in) {
        name = in.readString();
        url = in.readString();
    }

    public static final Creator<PokemonAbility> CREATOR = new Creator<PokemonAbility>() {
        @Override
        public PokemonAbility createFromParcel(Parcel in) {
            return new PokemonAbility(in);
        }

        @Override
        public PokemonAbility[] newArray(int size) {
            return new PokemonAbility[size];
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
