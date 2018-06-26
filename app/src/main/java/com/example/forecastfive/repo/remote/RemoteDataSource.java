package com.example.forecastfive.repo.remote;

import android.support.annotation.NonNull;

import com.example.forecastfive.common.Constants;
import com.example.forecastfive.model.Forecast;
import com.example.forecastfive.repo.DataSource;
import com.example.forecastfive.retrofit.OpenWeatherService;

import io.reactivex.Observable;

public class RemoteDataSource implements DataSource {
    private final OpenWeatherService openWeatherService;

    public RemoteDataSource(OpenWeatherService openWeatherService) {
        this.openWeatherService = openWeatherService;
    }

    @Override
    public Observable<Forecast> getForecast(@NonNull String cityId) {
        return openWeatherService.getFiveDayForecast(cityId, Constants.API_KEY);
    }

    @Override
    public void storeForecast(Forecast forecast) {
        //no op
    }
}
