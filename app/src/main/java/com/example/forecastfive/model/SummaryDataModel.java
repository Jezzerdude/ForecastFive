package com.example.forecastfive.model;

public class SummaryDataModel {

    private final String day;
    private final String time;
    private final String condition;
    private final String temperature;
    private final String iconUrl;

    public SummaryDataModel(String day, String time, String condition, String temperature, String iconUrl) {
        this.day = day;
        this.time = time;
        this.condition = condition;
        this.temperature = temperature;
        this.iconUrl = iconUrl;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public String getCondition() {
        return condition;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getIconUrl() {
        return iconUrl;
    }
}
