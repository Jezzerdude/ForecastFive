package com.example.forecastfive.common;

public class Constants {

//API
public static final String GLASGOW_CITY_ID = "2648579";
public static final String API_KEY = "e87978f0ef99fd866adffe6a767099d3";
public static final String BASE_URL = "https://api.openweathermap.org/";
public static final String FIVE_DAY_WEATHER = "data/2.5/forecast";
public static final String ICON = "img/w/";


//DAGGER
public static final String OK_HTTP_CACHE_LIMIT_KEY = "OkHttpCacheLimit";
public static final int OK_HTTP_CACHE_LIMIT = 10 * 1024 * 1024;

//RETROFIT
public static final long CONNECT_TIMEOUT = 30; //seconds
public static final long READ_TIMEOUT = 30;
}
