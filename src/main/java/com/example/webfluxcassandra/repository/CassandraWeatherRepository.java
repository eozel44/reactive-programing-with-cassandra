package com.example.webfluxcassandra.repository;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.example.webfluxcassandra.domain.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.ReactiveCassandraOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class CassandraWeatherRepository implements WeatherRepository {
    private final ReactiveCassandraOperations cassandraTemplate;

    @Autowired
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
    public Mono<Void> delete(Weather weather) {
        return this.cassandraTemplate.delete(weather).then();
    }

    @Override
    public Flux<Weather> findByCity(String city) {
        Select select = QueryBuilder.select().from("weather");
        select.where(QueryBuilder.eq("city", city));
        System.out.println(select.toString());
        return this.cassandraTemplate.select(select,Weather.class);
    }
}
