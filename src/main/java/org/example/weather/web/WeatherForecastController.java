package org.example.weather.web;

import org.example.weather.model.WeatherForecast;
import org.example.weather.service.IWeatherForecastService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WeatherForecastController {
    private final IWeatherForecastService weatherForecastService;

    public WeatherForecastController(IWeatherForecastService weatherForecastService) {
        this.weatherForecastService = weatherForecastService;
    }
    @GetMapping("/all")
    public List<WeatherForecast> getAllForecasts() {
        return weatherForecastService.findAll();
    }

    @GetMapping("/above25")
    public List<WeatherForecast> getAbove25(){
       return weatherForecastService.findByAbove25True();
    }

    @PostMapping("/fetch")
    public String fetchData(){
        weatherForecastService.fetchForecast("Kočani",41.9147, 22.4085) ;
        weatherForecastService.fetchForecast("Kavadarci",41.4329, 22.0089);
        weatherForecastService.fetchForecast("Ohrid",41.1231, 20.8016);
        return "Weather saved";
    }

}
