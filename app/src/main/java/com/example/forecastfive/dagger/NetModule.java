package com.example.forecastfive.dagger;

import android.content.Context;

import com.example.forecastfive.common.Constants;
import com.example.forecastfive.retrofit.OpenWeatherService;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.forecastfive.common.Constants.BASE_URL;
import static com.example.forecastfive.common.Constants.CONNECT_TIMEOUT;
import static com.example.forecastfive.common.Constants.OK_HTTP_CACHE_LIMIT;
import static com.example.forecastfive.common.Constants.OK_HTTP_CACHE_LIMIT_KEY;
import static com.example.forecastfive.common.Constants.READ_TIMEOUT;

@Module
public class NetModule {

  @Provides
  @Singleton
  @Named(Constants.OK_HTTP_CACHE_LIMIT_KEY)
  public int provideOkHttpCacheSize() {
      return OK_HTTP_CACHE_LIMIT;
  }

  @Provides
  @Singleton
  public Cache provideOkHttpCache(Context context, @Named(OK_HTTP_CACHE_LIMIT_KEY) int cacheLimit) {
      return new Cache(context.getCacheDir(), cacheLimit);
  }

  @Provides
  @Singleton
  public OkHttpClient provideOkHttpClient(Cache cache) {
      return new OkHttpClient.Builder()
              .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
              .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
              .cache(cache)
              .build();
  }

  @Provides
  @Singleton
  public Retrofit provideRetrofitClient(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
  }

  @Provides
  @Singleton
  public OpenWeatherService provideForecastService(Retrofit retrofit) {
      return retrofit.create(OpenWeatherService.class);
  }
}
