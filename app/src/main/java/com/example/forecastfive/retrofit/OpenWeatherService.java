package com.example.forecastfive.retrofit;


import com.example.forecastfive.common.Constants;
import com.example.forecastfive.model.Forecast;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherService {

    @GET(Constants.FIVE_DAY_WEATHER)
    Observable<Forecast> getFiveDayForecast(@Query("id") String cityId, @Query("APPID") String apiKey);

}
