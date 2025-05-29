package org.example.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class WeatherApplication {
    @Autowired
    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(WeatherApplication.class, args);
    }

}
