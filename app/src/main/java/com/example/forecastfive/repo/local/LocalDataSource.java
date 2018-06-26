package com.example.forecastfive.repo.local;

import android.support.annotation.NonNull;

import com.example.forecastfive.model.Forecast;
import com.example.forecastfive.realm.RealmManager;
import com.example.forecastfive.repo.DataSource;

import io.reactivex.Observable;

public class LocalDataSource implements DataSource {

    private final RealmManager realmManager;

    public LocalDataSource(RealmManager realmManager) {
        this.realmManager = realmManager;
    }

    @Override
    public Observable<Forecast> getForecast(@NonNull String cityId) {
        return realmManager.getRealmInstance()
                .where(Forecast.class)
                .findFirstAsync()
                .<Forecast>asFlowable()
                .filter(realmObject -> realmObject.isLoaded())
                .take(1)
                .toObservable()
                .flatMap(forecast -> {
                    if (forecast.isValid()) {
                        return Observable.just(forecast);
                    } else {
                        return Observable.error(new IllegalStateException("Data not available in cache"));
                    }
                })
                .doOnTerminate(realmManager::closeRealmInstance);
    }

    @Override
    public void storeForecast(Forecast forecast) {
        try {
            realmManager.getRealmInstance().executeTransactionAsync(realm ->
                    realm.insertOrUpdate(forecast));
        } finally {
            realmManager.closeRealmInstance();
        }

    }


}
