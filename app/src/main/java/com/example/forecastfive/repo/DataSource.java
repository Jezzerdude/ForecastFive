package com.example.forecastfive.repo;

import android.support.annotation.NonNull;

import com.example.forecastfive.model.Forecast;

import io.reactivex.Observable;

public interface DataSource {
    Observable<Forecast> getForecast(@NonNull String cityId);
    void storeForecast(Forecast forecast);
}
