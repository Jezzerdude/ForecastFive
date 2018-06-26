package com.example.forecastfive;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;

import com.example.forecastfive.common.Generators;
import com.example.forecastfive.model.Forecast;
import com.example.forecastfive.model.Prediction;
import com.example.forecastfive.model.SummaryDataModel;
import com.example.forecastfive.repo.ForecastRepository;
import com.example.forecastfive.summary.SummaryViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;
import io.realm.RealmList;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Generators.class)

public class SummaryViewModelTest {

    @Rule
    private InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private ForecastRepository forecastRepository;
    @Mock
    private Observer<List<SummaryDataModel>> observer;
    @Mock
    private Forecast forecast;

    private final RealmList<Prediction> predictions = new RealmList<>();
    private final List<SummaryDataModel> models = Collections.emptyList();

    private SummaryViewModel summaryViewModel;


    @BeforeClass
    public static void setupRxSchedulers() {
        Scheduler scheduler = new Scheduler() {
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run);
            }
        };
        RxJavaPlugins.setInitIoSchedulerHandler(schedulerCallable -> scheduler);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> scheduler);
    }

    @Before
    public void setup() {
        Mockito.when(forecastRepository.getForecast(Mockito.anyString())).thenReturn(Observable.just(forecast));
        Mockito.when(forecast.getPredictions()).thenReturn(predictions);
        PowerMockito.mockStatic(Generators.class);
        Mockito.when(Generators.getSummaryDataModel(predictions)).thenReturn(models);
        summaryViewModel = new SummaryViewModel(forecastRepository);
    }

    @Test
    public void testGetData() {
        summaryViewModel.getData().observeForever(observer);
        Mockito.verify(observer).onChanged(Collections.emptyList());
    }

    @After
    public void tearDown() {
        RxJavaPlugins.reset();
        RxAndroidPlugins.reset();
    }
}
