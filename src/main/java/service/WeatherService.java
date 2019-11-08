package service;

import domain.Weather;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface WeatherService {
    Mono<Weather> save(Weather weather);
    Mono<Weather> update(Weather weather);
    Mono<Weather> findOne(UUID weatherId);
    Mono<Boolean> delete(UUID weatherId);
    Flux<Weather> findByCity(String city);
}
