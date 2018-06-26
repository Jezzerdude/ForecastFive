package com.example.forecastfive;

import android.app.Application;

import com.example.forecastfive.dagger.AppComponent;
import com.example.forecastfive.dagger.AppModule;
import com.example.forecastfive.dagger.DaggerAppComponent;
import com.example.forecastfive.dagger.NetModule;

import net.danlew.android.joda.JodaTimeAndroid;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ForecastFiveApp extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                            .appModule(new AppModule(this))
                            .netModule(new NetModule())
                            .build();

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfiguration);

        JodaTimeAndroid.init(this);

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
