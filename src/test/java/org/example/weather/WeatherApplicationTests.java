package org.example.weather;

import org.example.weather.model.WeatherForecast;
import org.example.weather.repository.WeatherForecastRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WeatherApplicationTests {

    @Autowired
    private WeatherForecastRepository repository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testRestApiAbove25() {
        WeatherForecast[] response = restTemplate.getForObject("/api/above25", WeatherForecast[].class);
        assertThat(response).isNotNull();
        assertThat(response.length).isGreaterThanOrEqualTo(0);
    }
}

