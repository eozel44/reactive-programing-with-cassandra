package repository;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import domain.Weather;
import org.springframework.data.cassandra.core.ReactiveCassandraOperations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class CassandraWeatherRepository implements WeatherRepository {
    private final ReactiveCassandraOperations cassandraTemplate;

    public CassandraWeatherRepository(ReactiveCassandraOperations cassandraTemplate) {
        this.cassandraTemplate = cassandraTemplate;
    }

    @Override
    public Mono<Weather> save(Weather weather) {
        return this.cassandraTemplate.insert(weather);
    }

    @Override
    public Mono<Weather> update(Weather weather) {
        return this.cassandraTemplate.update(weather);
    }

    @Override
    public Mono<Weather> findOne(UUID weatherId) {
        return this.cassandraTemplate.selectOneById(weatherId,Weather.class);
    }

    @Override
    public Mono<Boolean> delete(UUID weatherId) {
        return this.cassandraTemplate.deleteById(weatherId,Weather.class);
    }

    @Override
    public Flux<Weather> findByCity(String city) {
        Select select = QueryBuilder.select().from("weather");
        select.where(QueryBuilder.eq("city", city));
        return this.cassandraTemplate.select(select,Weather.class);
    }
}
