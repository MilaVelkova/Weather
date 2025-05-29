package org.example.weather.service;

import org.example.weather.model.WeatherForecast;

import java.util.List;

public interface IWeatherForecastService {
    List<WeatherForecast> findAll();
    List<WeatherForecast> findByAbove25True();
    void fetchForecast(String city, double lat, double lon);
}
