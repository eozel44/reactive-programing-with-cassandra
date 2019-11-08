package web;

import domain.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import service.WeatherService;

import java.util.UUID;

@Service
public class WeatherHandler {

    private final WeatherService weatherService;

    @Autowired
    public WeatherHandler(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public Mono<ServerResponse> get(ServerRequest request) {
        UUID uuid = UUID.fromString(request.pathVariable("id"));
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        return this.weatherService.findOne(uuid)
                .flatMap(weather -> ServerResponse.ok().body(Mono.just(weather), Weather.class))
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> save(ServerRequest serverRequest) {
        Mono<Weather> weatherToBeCreated = serverRequest.bodyToMono(Weather.class);
        return weatherToBeCreated.flatMap(weather ->
                ServerResponse.status(HttpStatus.CREATED).body(weatherService.save(weather), Weather.class)
        );
    }

    public Mono<ServerResponse> update(ServerRequest serverRequest) {
        Mono<Weather> weatherToBeUpdated = serverRequest.bodyToMono(Weather.class);

        return weatherToBeUpdated.flatMap(weather ->
                ServerResponse.status(HttpStatus.CREATED).body(weatherService.update(weather), Weather.class));
    }

    public Mono<ServerResponse> delete(ServerRequest serverRequest) {
        UUID uuid = UUID.fromString(serverRequest.pathVariable("id"));

        return this.weatherService.delete(uuid).flatMap(result -> ServerResponse.accepted().build());
    }

    public Mono<ServerResponse> findWeatherInCity(ServerRequest serverRequest) {
        String city = serverRequest.pathVariable("city");
        System.out.println(city);
        Flux<Weather> weathersByState = this.weatherService.findByCity(city);

        return ServerResponse.ok().body(weathersByState, Weather.class);
    }



}
