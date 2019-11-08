package domain;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Table("weather")
public class Weather implements Serializable {

    @PrimaryKey
    private UUID id;
    private String city;
    private LocalDateTime completeDate;
    private double temperature;

    public Weather() {
    }

    public Weather(
            UUID id,
            String city,
            LocalDateTime completeDate,
            double temperature) {
        this.id = id;
        this.city = city;
        this.completeDate = completeDate;
        this.temperature = temperature;

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDateTime getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(LocalDateTime completeDate) {
        this.completeDate = completeDate;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weather weather = (Weather) o;
        return Objects.equals(id, weather.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
