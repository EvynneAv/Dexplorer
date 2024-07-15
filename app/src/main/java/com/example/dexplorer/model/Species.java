package com.example.dexplorer.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Species implements Parcelable{
    private String name;
    private String url;

    public Species(String name) {
        this.name = name;
    }

    protected Species(Parcel in) {
        name = in.readString();
        url = in.readString();
    }


    public static final Creator<Species> CREATOR = new Creator<Species>() {
        @Override
        public Species createFromParcel(Parcel in) {
            return new Species(in);
        }

        @Override
        public Species[] newArray(int size) {
            return new Species[size];
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
