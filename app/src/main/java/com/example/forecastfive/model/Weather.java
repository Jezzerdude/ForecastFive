package com.example.forecastfive.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Weather extends RealmObject{
    @SerializedName("id")
    @Expose
    public int id;

    @SerializedName("condition")
    @Expose
    public String main;

    @SerializedName("description")
    @Expose
    public String description;

    @SerializedName("icon")
    @Expose
    public String icon;

    public Weather() {
    }

    public int getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

}
