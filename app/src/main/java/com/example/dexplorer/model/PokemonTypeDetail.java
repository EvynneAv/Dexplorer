package com.example.dexplorer.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class PokemonTypeDetail implements Parcelable {
    @SerializedName("name")
    private String name;
    @SerializedName("url")
    private String url;

    protected PokemonTypeDetail(Parcel in) {
        name = in.readString();
        url = in.readString();
    }


    public static final Creator<PokemonTypeDetail> CREATOR = new Creator<PokemonTypeDetail>() {
        @Override
        public PokemonTypeDetail createFromParcel(Parcel in) {
            return new PokemonTypeDetail(in);
        }

        @Override
        public PokemonTypeDetail[] newArray(int size) {
            return new PokemonTypeDetail[size];
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

    public PokemonTypeDetail(String name, String url) {
        this.name = name;
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
