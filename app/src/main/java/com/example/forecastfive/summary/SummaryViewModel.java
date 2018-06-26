package com.example.forecastfive.summary;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

import com.example.forecastfive.common.Constants;
import com.example.forecastfive.common.Generators;
import com.example.forecastfive.model.SummaryDataModel;
import com.example.forecastfive.repo.ForecastRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SummaryViewModel extends ViewModel {

    private final ForecastRepository forecastRepository;
    private final CompositeDisposable compositeDisposable;
    private final MutableLiveData<List<SummaryDataModel>> forecastData = new MutableLiveData<>();

    public final ObservableBoolean progressVisbility = new ObservableBoolean(false);
    public final ObservableBoolean errorLayoutVisibility = new ObservableBoolean(false);

    public SummaryViewModel(@NonNull ForecastRepository forecastRepository) {
        this.forecastRepository = forecastRepository;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }


    public LiveData<List<SummaryDataModel>> getData() {
        loadData();
        return forecastData;
    }

    private void loadData() {
        compositeDisposable.add(forecastRepository.getForecast(Constants.GLASGOW_CITY_ID)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> progressVisbility.set(true))
                .map(forecast -> Generators.getSummaryDataModel(forecast.getPredictions()))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(models -> {
                    progressVisbility.set(false);
                    errorLayoutVisibility.set(models.isEmpty());
                })
                .doOnTerminate(() -> progressVisbility.set(false))
                .doOnError(throwable -> errorLayoutVisibility.set(true))
                .subscribe(forecastData::setValue, Throwable::printStackTrace));
    }


}
