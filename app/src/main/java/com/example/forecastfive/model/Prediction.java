package com.example.forecastfive.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Prediction extends RealmObject{
    @SerializedName("dt")
    @Expose
    public long dateAndTime;

    @SerializedName("main")
    @Expose
    public Condition condition;

    @SerializedName("weather")
    @Expose
    public RealmList<Weather> weather = null;


    public Prediction() {
    }

    public long getDateAndTime() {
        return dateAndTime;
    }

    public RealmList<Weather> getWeather() {
        return weather;
    }

    public Condition getCondition() {

        return condition;
    }
}
