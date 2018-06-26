package com.example.forecastfive.dagger;

import android.app.Application;
import android.content.Context;


import com.example.forecastfive.summary.dagger.Summarycomponent;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

@Module(subcomponents = Summarycomponent.class)
public class AppModule {

    private final Application application;

    public AppModule(Application app) {
        this.application = app;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return application;
    }

}
