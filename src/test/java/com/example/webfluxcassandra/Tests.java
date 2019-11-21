package com.example.webfluxcassandra;

import com.example.webfluxcassandra.domain.Weather;
import com.example.webfluxcassandra.repository.WeatherRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Tests {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    WeatherRepository weatherRepository;

    @Test
    public void testCreateWeather() {
        Weather weather = new Weather(UUID.randomUUID(), "erzurum", LocalDateTime.now(), 10d);

        webTestClient.post().uri("/weathers/create")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(weather), Weather.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.city").isEqualTo("erzurum");
    }


    @Test
    public void testGetAllWeathers() {
        webTestClient.get().uri("/weathers")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(Weather.class);
    }



    @Test
    public void testGetSingleWeather() {
        Weather weather = weatherRepository.save(new Weather(UUID.randomUUID(), "ankara", LocalDateTime.now(), 22d)).block();

        webTestClient.get()
                .uri("/weathers/{id}", Collections.singletonMap("id", weather.getId()))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(response ->
                        Assertions.assertThat(response.getResponseBody()).isNotNull());
    }

    @Test
    public void testUpdateWeather() {

        UUID id = UUID.randomUUID();
        Weather weather = weatherRepository.save(new Weather(id, "hakkari", LocalDateTime.now(), 22d)).block();
        Weather newWeatherData = new Weather(id, "hakkari", LocalDateTime.now(), 35d);


        webTestClient.put()
                .uri("/weathers/update")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(newWeatherData), Weather.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$.temperature").isEqualTo(35d);
    }

    @Test
    public void testDeleteWeather() {
        Weather weather = weatherRepository.save(new Weather(UUID.randomUUID(), "trabzon", LocalDateTime.now(), 22d)).block();

        webTestClient.delete()
                .uri("/weathers/{id}", Collections.singletonMap("id",  weather.getId()))
                .exchange()
                .expectStatus().isOk();
    }
}
