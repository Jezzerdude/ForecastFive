package com.example.forecastfive.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Condition extends RealmObject{
    @SerializedName("temp")
    @Expose
    public double temp;

    @SerializedName("temp_min")
    @Expose
    public double tempMin;

    @SerializedName("temp_max")
    @Expose
    public double tempMax;

    @SerializedName("pressure")
    @Expose
    public double pressure;

    @SerializedName("sea_level")
    @Expose
    public double seaLevel;

    @SerializedName("grnd_level")
    @Expose
    public double grndLevel;

    @SerializedName("humidity")
    @Expose
    public double humidity;

    @SerializedName("temp_kf")
    @Expose
    public double tempKf;

    public Condition() {
    }

    public double getTemp() {
        return temp;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public double getPressure() {
        return pressure;
    }

    public double getSeaLevel() {
        return seaLevel;
    }

    public double getGrndLevel() {
        return grndLevel;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getTempKf() {
        return tempKf;
    }
}
