package com.example.webfluxcassandra.repository;

import com.example.webfluxcassandra.domain.Weather;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface WeatherRepository {

    Mono<Weather> save(Weather weather);
    Mono<Weather> update(Weather weather);
    Mono<Weather> findOne(UUID weatherId);
    Mono<Void> delete(Weather weather);
    Flux<Weather> findByCity(String city);
}
