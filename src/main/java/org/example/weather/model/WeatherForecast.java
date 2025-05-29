package org.example.weather.model;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "weather_forecast")
public class WeatherForecast {
    public WeatherForecast() {
    }
    public WeatherForecast(String city, LocalDate forecastDate, float maxTemp, boolean above25) {
        this.city = city;
        this.forecastDate = forecastDate;
        this.maxTemp = maxTemp;
        this.above25 = above25;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    private LocalDate forecastDate;

    private float maxTemp;

    private boolean above25;

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
    public LocalDate getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(LocalDate forecastDate) {
        this.forecastDate = forecastDate;
    }

    public float getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(float maxTemp) {
        this.maxTemp = maxTemp;
    }

    public boolean getAbove25() {
        return above25;
    }

    public void setAbove25(boolean above25) {
        this.above25 = above25;
    }
}

