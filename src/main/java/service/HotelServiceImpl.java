package service;

import domain.Hotel;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import repository.HotelRepository;

import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Mono<Hotel> save(Hotel hotel) {
        if (hotel.getId() == null) {
            hotel.setId(UUID.randomUUID());
        }
        return this.hotelRepository.save(hotel);
    }

    @Override
    public Mono<Hotel> update(Hotel hotel) {
        return null;
    }

    @Override
    public Mono<Hotel> findOne(UUID uuid) {
        return null;
    }

    @Override
    public Mono<Boolean> delete(UUID uuid) {
        return null;
    }

    @Override
    public Flux<Hotel> findHotelsInState(String state) {
        return null;
    }
}
