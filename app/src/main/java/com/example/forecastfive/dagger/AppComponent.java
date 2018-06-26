package com.example.forecastfive.dagger;


import com.example.forecastfive.summary.SummaryActivity;
import com.example.forecastfive.repo.ForecastRepository;
import com.example.forecastfive.summary.dagger.Summarycomponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, StorageModule.class, NetModule.class})
public interface AppComponent {
    ForecastRepository forecastRepository();
    Summarycomponent.Builder summaryComponentBuilder();
}
