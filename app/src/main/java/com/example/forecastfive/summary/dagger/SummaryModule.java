package com.example.forecastfive.summary.dagger;

import com.example.forecastfive.repo.ForecastRepository;
import com.example.forecastfive.summary.SummaryViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class SummaryModule {

    @SummaryScope
    @Provides
    public SummaryViewModelFactory provideSummaryViewModelFactory(ForecastRepository forecastRepository) {
        return new SummaryViewModelFactory(forecastRepository);
    }
}
