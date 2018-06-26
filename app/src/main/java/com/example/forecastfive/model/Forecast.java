package com.example.forecastfive.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Forecast extends RealmObject {

    @PrimaryKey
    public String id;

    @SerializedName("list")
    @Expose
    public RealmList<Prediction> predictions = null;

    @SerializedName("city")
    @Expose
    public City city;

    public Forecast() {
    }

    public String getId() {
        return id;
    }

    public RealmList<Prediction> getPredictions() {
        return predictions;
    }

    public City getCity() {
        return city;
    }
}
