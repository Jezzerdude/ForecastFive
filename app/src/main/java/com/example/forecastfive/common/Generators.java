package com.example.forecastfive.common;

import android.support.annotation.Nullable;
import android.util.Log;

import com.example.forecastfive.model.Prediction;
import com.example.forecastfive.model.SummaryDataModel;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Generators {

    private static final String CELSIUS_SIGN = "°C";
    private static final String FAHRENHEIT_SIGN = "°C";
    private static final String ICON_BASE_URL = "http://openweathermap.org/img/w/";
    private static final double KELVIN_FACTOR =   273.15;



    @Nullable
    public static List<SummaryDataModel> getSummaryDataModel(List<Prediction> predictions) {
        try {
            final List<SummaryDataModel> models = new ArrayList<>();
            for (Prediction prediction : predictions) {
                models.add(new SummaryDataModel(readableDateFromUtc(prediction.getDateAndTime()),
                        readableTimeFromUtc(prediction.getDateAndTime()), prediction.getWeather().get(0).getDescription(),
                        getTemperature(true, prediction.getCondition().getTemp()),
                        ICON_BASE_URL + prediction.getWeather().get(0).getIcon() + ".png"));
            }
            return models;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String readableDateFromUtc(long utcValue) {
        final DateTime dateTime = getDateTime(utcValue);

        if (LocalDate.now().compareTo(new LocalDate(dateTime)) == 0) {
            return "Today";
        } else if (LocalDate.now().plusDays(1).compareTo(new LocalDate(dateTime)) == 0) {
            return "Tomorrow";
        } else {
            final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("E d MMMM")
                    .withLocale(Locale.getDefault());
            return dateTimeFormatter.print(dateTime);
        }

    }

    private static String getTemperature(boolean isMetric, double kelvinValue) {
        Log.d("Generators", String.valueOf(kelvinValue));
        final double convertedValue =  kelvinValue - KELVIN_FACTOR;
        final BigDecimal bigDecimal = new BigDecimal(convertedValue).setScale(1, RoundingMode.HALF_UP);
        return String.valueOf((bigDecimal.doubleValue()) + (isMetric ? CELSIUS_SIGN : FAHRENHEIT_SIGN));
    }

    private static String readableTimeFromUtc(long utcValue) {
        final DateTime dateTime = getDateTime(utcValue);
        final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("h:m a")
                .withLocale(Locale.getDefault());
        return dateTimeFormatter.print(dateTime);
    }

    private static DateTime getDateTime(long utcValue) {
        return new DateTime(utcValue*1000L, DateTimeZone.getDefault());
    }


}
