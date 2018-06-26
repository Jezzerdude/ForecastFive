package com.example.forecastfive.dagger;

import com.example.forecastfive.realm.RealmManager;
import com.example.forecastfive.repo.DataSource;
import com.example.forecastfive.repo.local.LocalDataSource;
import com.example.forecastfive.repo.remote.RemoteDataSource;
import com.example.forecastfive.retrofit.OpenWeatherService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class StorageModule {

    @Provides
    @Singleton
    public RealmManager provideRealmManager() {
        return new RealmManager();
    }

    @Provides
    @Singleton
    @Local
    public DataSource provideLocalDataSource(RealmManager realmManager) {
        return new LocalDataSource(realmManager);
    }

    @Provides
    @Singleton
    @Remote
    public DataSource provideRemoteDataSource(OpenWeatherService openWeatherService) {
        return new RemoteDataSource(openWeatherService);
    }
}
