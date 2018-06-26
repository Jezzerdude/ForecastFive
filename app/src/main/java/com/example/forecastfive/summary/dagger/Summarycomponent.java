package com.example.forecastfive.summary.dagger;

import com.example.forecastfive.summary.SummaryActivity;

import dagger.Subcomponent;

@SummaryScope
@Subcomponent(modules = SummaryModule.class)
public interface Summarycomponent {
    public void inject(SummaryActivity summaryActivity);

    @Subcomponent.Builder
    interface Builder {
        Builder summaryModule(SummaryModule summaryModule);
        Summarycomponent build();
    }
}
