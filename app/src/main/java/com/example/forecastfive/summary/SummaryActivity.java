package com.example.forecastfive.summary;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.forecastfive.ForecastFiveApp;
import com.example.forecastfive.R;
import com.example.forecastfive.databinding.ActivityMainBinding;

import javax.inject.Inject;

public class SummaryActivity extends AppCompatActivity {

    @Inject
    SummaryViewModelFactory summaryViewModelFactory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ((ForecastFiveApp) getApplication()).getAppComponent().summaryComponentBuilder()
                .build().inject(this);

        final RecyclerView recyclerView = findViewById(R.id.recyclerview_summary);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final SummaryAdapter summaryAdapter = new SummaryAdapter();
        recyclerView.setAdapter(summaryAdapter);

        final SummaryViewModel summaryViewModel = ViewModelProviders.of(this, summaryViewModelFactory)
                .get(SummaryViewModel.class);
        activityMainBinding.setProgressVisibility(summaryViewModel.progressVisbility);
        activityMainBinding.setErrorLayoutVisibility(summaryViewModel.errorLayoutVisibility);
        summaryViewModel.getData().observe(this, summaryAdapter::updateData);
    }
}
