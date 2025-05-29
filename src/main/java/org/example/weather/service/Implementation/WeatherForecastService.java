package org.example.weather.service.Implementation;

import jakarta.transaction.Transactional;
import org.example.weather.model.WeatherForecast;
import org.example.weather.repository.WeatherForecastRepository;
import org.example.weather.service.IWeatherForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class WeatherForecastService implements IWeatherForecastService {

    @Autowired
    private Environment env;

    private final WeatherForecastRepository weatherForecastRepository;

    private static final String API_URL = "https://api.openweathermap.org/data/2.5/forecast/daily?lat=%s&lon=%s&cnt=16&appid=%s&units=metric";

    public WeatherForecastService(WeatherForecastRepository weatherForecastRepository) {
        this.weatherForecastRepository = weatherForecastRepository;
    }

    @Override
    public List<WeatherForecast> findAll() {
        return weatherForecastRepository.findAll();
    }

    @Override
    public List<WeatherForecast> findByAbove25True() {
        return weatherForecastRepository.findByAbove25True();
    }

    @Transactional
    public void fetchForecast(String city, double lat, double lon) {

        RestTemplate restTemplate = new RestTemplate();
        String API_KEY = env.getProperty("env.data.API_KEY");
        String url = String.format(API_URL, lat, lon, API_KEY);
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        List<Map<String, Object>> days = (List<Map<String, Object>>) response.get("list");

        List<WeatherForecast> weatherForecasts = new ArrayList<>();

        for (Map<String, Object> day : days) {
            double tempMax = ((Map<String, Number>) day.get("temp")).get("max").doubleValue();
            long dt = ((Number) day.get("dt")).longValue();
            LocalDate date = LocalDate.ofInstant(java.time.Instant.ofEpochSecond(dt), ZoneId.systemDefault());

            weatherForecasts.add(new WeatherForecast(city, date, (float) tempMax, tempMax > 25));
        }

        weatherForecastRepository.saveAll(weatherForecasts);
    }
}
