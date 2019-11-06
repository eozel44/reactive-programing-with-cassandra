package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import service.HotelService;
import domain.Hotel;

import java.util.UUID;


@Service
public class HotelHandler{

    private final HotelService hotelService;

    @Autowired
    public HotelHandler(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    public Mono<ServerResponse> hello(ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromObject("Hello, Spring!"));

    }

    public Mono<ServerResponse> get(ServerRequest request) {
        UUID uuid = UUID.fromString(request.pathVariable("id"));
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        return this.hotelService.findOne(uuid)
                .flatMap(hotel -> ServerResponse.ok().body(Mono.just(hotel), Hotel.class))
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> save(ServerRequest serverRequest) {
        Mono<Hotel> hotelToBeCreated = serverRequest.bodyToMono(Hotel.class);
        return hotelToBeCreated.flatMap(hotel ->
                ServerResponse.status(HttpStatus.CREATED).body(hotelService.save(hotel), Hotel.class)
        );
    }

    public Mono<ServerResponse> update(ServerRequest serverRequest) {
        Mono<Hotel> hotelToBeUpdated = serverRequest.bodyToMono(Hotel.class);

        return hotelToBeUpdated.flatMap(hotel ->
                ServerResponse.status(HttpStatus.CREATED).body(hotelService.update(hotel), Hotel.class));
    }

    public Mono<ServerResponse> delete(ServerRequest serverRequest) {
        UUID uuid = UUID.fromString(serverRequest.pathVariable("id"));

        return this.hotelService.delete(uuid).flatMap(result -> ServerResponse.accepted().build());
    }

    public Mono<ServerResponse> findHotelsWithLetter(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> findHotelsInState(ServerRequest serverRequest) {
        String state = serverRequest.pathVariable("state");
        Flux<Hotel> hotelsByState = this.hotelService.findHotelsInState(state);

        return ServerResponse.ok().body(hotelsByState, Hotel.class);
    }


}
