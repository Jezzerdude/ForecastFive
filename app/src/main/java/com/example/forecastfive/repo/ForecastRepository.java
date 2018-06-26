package com.example.forecastfive.repo;

import android.support.annotation.NonNull;

import com.example.forecastfive.dagger.Local;
import com.example.forecastfive.dagger.Remote;
import com.example.forecastfive.model.Forecast;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class ForecastRepository implements DataSource {

    private final DataSource localDataSource;
    private final DataSource remoteDataSource;

    @Inject
    public ForecastRepository(@Local DataSource localDataSource,
                              @Remote DataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }


    @Override
    public Observable<Forecast> getForecast(@NonNull String cityId) {
        return remoteDataSource.getForecast(cityId)
                .doOnNext(this::storeForecast)
                .doOnError(Throwable::printStackTrace)
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(localDataSource.getForecast(cityId));
    }

    @Override
    public void storeForecast(Forecast forecast) {
        localDataSource.storeForecast(forecast);
    }
}
