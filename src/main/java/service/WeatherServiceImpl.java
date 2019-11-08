package service;

import domain.Weather;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import repository.WeatherRepository;

import java.util.UUID;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;

    public WeatherServiceImpl(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Override
    public Mono<Weather> save(Weather weather) {
        if (weather.getId() == null) {
            weather.setId(UUID.randomUUID());
        }
        return this.weatherRepository.save(weather);
    }

    @Override
    public Mono<Weather> update(Weather weather) {
        return null;
    }

    @Override
    public Mono<Weather> findOne(UUID weatherId) {
        return this.weatherRepository.findOne(weatherId);
    }

    @Override
    public Mono<Boolean> delete(UUID weatherId) {
        return null;
    }

    @Override
    public Flux<Weather> findByCity(String city) {
        return this.weatherRepository.findByCity(city);
    }
}
