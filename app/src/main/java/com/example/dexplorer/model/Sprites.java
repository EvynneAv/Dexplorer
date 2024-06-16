package com.example.dexplorer.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Sprites implements Parcelable {
    @SerializedName("back_default")
    private String backDefault;
    @SerializedName("back_female")
    private String backFemale;
    @SerializedName("back_shiny")
    private String backShiny;
    @SerializedName("back_shiny_female")
    private String backShinyFemale;
    @SerializedName("front_default")
    private String frontDefault;
    @SerializedName("front_female")
    private String frontFemale;
    @SerializedName("front_shiny")
    private String frontShiny;
    @SerializedName("front_shiny_female")
    private String frontShinyFemale;

    public Sprites(String backDefault, String backFemale, String backShiny, String backShinyFemale, String frontDefault, String frontFemale, String frontShiny, String frontShinyFemale) {
        this.backDefault = backDefault;
        this.backFemale = backFemale;
        this.backShiny = backShiny;
        this.backShinyFemale = backShinyFemale;
        this.frontDefault = frontDefault;
        this.frontFemale = frontFemale;
        this.frontShiny = frontShiny;
        this.frontShinyFemale = frontShinyFemale;
    }

    protected Sprites(Parcel in) {
        backDefault = in.readString();
        backFemale = in.readString();
        backShiny = in.readString();
        backShinyFemale = in.readString();
        frontDefault = in.readString();
        frontFemale = in.readString();
        frontShiny = in.readString();
        frontShinyFemale = in.readString();
    }

    public static final Creator<Sprites> CREATOR = new Creator<Sprites>() {
        @Override
        public Sprites createFromParcel(Parcel in) {
            return new Sprites(in);
        }

        @Override
        public Sprites[] newArray(int size) {
            return new Sprites[size];
        }
    };

    public String getBackDefault() {
        return backDefault;
    }

    public void setBackDefault(String backDefault) {
        this.backDefault = backDefault;
    }

    public String getBackFemale() {
        return backFemale;
    }

    public void setBackFemale(String backFemale) {
        this.backFemale = backFemale;
    }

    public String getBackShiny() {
        return backShiny;
    }

    public void setBackShiny(String backShiny) {
        this.backShiny = backShiny;
    }

    public String getBackShinyFemale() {
        return backShinyFemale;
    }

    public void setBackShinyFemale(String backShinyFemale) {
        this.backShinyFemale = backShinyFemale;
    }

    public String getFrontDefault() {

        Log.d("Dentro do front defaul", "Rodou o front default");
        return frontDefault;
    }

    public void setFrontDefault(String frontDefault) {
        this.frontDefault = frontDefault;
    }

    public String getFrontFemale() {
        return frontFemale;
    }

    public void setFrontFemale(String frontFemale) {
        this.frontFemale = frontFemale;
    }

    public String getFrontShiny() {
        return frontShiny;
    }

    public void setFrontShiny(String frontShiny) {
        this.frontShiny = frontShiny;
    }

    public String getFrontShinyFemale() {
        return frontShinyFemale;
    }

    public void setFrontShinyFemale(String frontShinyFemale) {
        this.frontShinyFemale = frontShinyFemale;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(backDefault);
        dest.writeString(backFemale);
        dest.writeString(backShiny);
        dest.writeString(backShinyFemale);
        dest.writeString(frontDefault);
        dest.writeString(frontFemale);
        dest.writeString(frontShiny);
        dest.writeString(frontShinyFemale);
    }
}
