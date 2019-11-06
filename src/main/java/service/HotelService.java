package service;

import domain.Hotel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface HotelService {
    Mono<Hotel> save(Hotel hotel);
    Mono<Hotel> update(Hotel hotel);
    Mono<Hotel> findOne(UUID uuid);
    Mono<Boolean> delete(UUID uuid);
    Flux<Hotel> findHotelsInState(String state);
}
