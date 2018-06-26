package com.example.forecastfive.summary;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.forecastfive.repo.ForecastRepository;

public class SummaryViewModelFactory implements ViewModelProvider.Factory {

    private final ForecastRepository forecastRepository;

    public SummaryViewModelFactory(@NonNull ForecastRepository forecastRepository) {
        this.forecastRepository = forecastRepository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked") //Due to method definitions in ViewModelProvider.Factory

    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(SummaryViewModel.class)) {
            return  (T) new SummaryViewModel(forecastRepository);
        }

        throw new IllegalArgumentException("Unknown class");
    }
}
