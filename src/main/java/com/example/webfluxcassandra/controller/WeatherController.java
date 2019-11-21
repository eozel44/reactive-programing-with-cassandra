package com.example.webfluxcassandra.controller;

import com.example.webfluxcassandra.domain.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.example.webfluxcassandra.repository.WeatherRepository;

import javax.validation.Valid;
import java.util.UUID;

@RestController
public class WeatherController {

    @Autowired
    private WeatherRepository weatherRepository;
    /*
    private final WeatherRepository weatherRepository;

    public WeatherController(WeatherRepository weatherRepository){
        this.weatherRepository = weatherRepository;
    }
*/

    @PostMapping(value = "/weathers/create")
    public Mono<Weather> save(@Valid @RequestBody Weather weather) {
        if (weather.getId() == null) {
            weather.setId(UUID.randomUUID());
        }
        return weatherRepository.save(weather);
    }

    @PutMapping(value = "/weathers/update")
    public Mono<ResponseEntity<Weather>> update(@Valid @RequestBody Weather weather) {
        return weatherRepository.findOne(weather.getId())
                .flatMap(existingWeather -> {
                    existingWeather.setCompleteDate(weather.getCompleteDate());
                    existingWeather.setTemperature(weather.getTemperature());
                    return weatherRepository.save(existingWeather);
                })
                .map(updateTweet -> new ResponseEntity<>(updateTweet, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/weathers/{id}")
    public Mono<ResponseEntity<Weather>> findOne(@PathVariable(value = "id") UUID weatherId) {
        return weatherRepository.findOne(weatherId)
                .map(weather -> ResponseEntity.ok(weather))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/weathers/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable(value = "id") UUID weatherId) {
        return weatherRepository.findOne(weatherId)
                .flatMap(existingWeather ->
                        weatherRepository.delete(existingWeather)
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/weathers/fromcity/{city}")
    public Flux<Weather> findByCity(@PathVariable(value = "city") String city) {
        return weatherRepository.findByCity(city);
    }

    @GetMapping("/weathers")
    public Flux<Weather> getAllWeathers() {
        return weatherRepository.getAllWeathers();
    }
}
