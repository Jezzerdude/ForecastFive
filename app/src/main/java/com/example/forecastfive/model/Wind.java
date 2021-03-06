package com.example.forecastfive.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Wind extends RealmObject{
    @SerializedName("speed")
    @Expose
    public double speed;

    @SerializedName("deg")
    @Expose
    public double deg;

    public Wind() {
    }
}
