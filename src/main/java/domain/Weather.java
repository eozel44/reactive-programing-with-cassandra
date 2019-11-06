package domain;

import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDateTime;

@Table("weather")
public class Weather implements Serializable {

    private int weatherStationId;
    private String weatherStationName;
    private double elevation;
    private double latitude;
    private double longitude;
    private String stationNumber;
    private String city;
    private String state;
    private LocalDateTime completeDate;
    private LocalDateTime observationDate;
    private int year;
    private int month;
    private int day;
    private double precipitation;
    private double pressure;
    private double pressureMax;
    private double pressureMin;
    private double radiation;
    private double temperature;

    public Weather(int weatherStationId,
            String weatherStationName,
            double elevation,
            double latitude,
            double longitude,
            String stationNumber,
            String city,
            String state,
            LocalDateTime completeDate,
            LocalDateTime observationDate,
            int year,
            int month,
            int day,
            double precipitation,
            double pressure,
            double pressureMax,
            double pressureMin,
            double radiation,
            double temperature) {
        this.weatherStationId = weatherStationId;
        this.weatherStationName = weatherStationName;
        this.elevation = elevation;
        this.latitude = latitude;
        this.longitude = longitude;
        this.stationNumber = stationNumber;
        this.city = city;
        this.state = state;
        this.completeDate = completeDate;
        this.observationDate = observationDate;
        this.year = year;
        this.month = month;
        this.day = day;
        this.precipitation = precipitation;
        this.pressure = pressure;
        this.pressureMax = pressureMax;
        this.pressureMin = pressureMin;
        this.radiation = radiation;
        this.temperature = temperature;

    }

    public double getElevation() {
        return elevation;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public double getPressure() {
        return pressure;
    }

    public double getPressureMax() {
        return pressureMax;
    }

    public double getPressureMin() {
        return pressureMin;
    }

    public double getRadiation() {
        return radiation;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getWeatherStationId() {
        return weatherStationId;
    }

    public int getYear() {
        return year;
    }

    public LocalDateTime getCompleteDate() {
        return completeDate;
    }

    public LocalDateTime getObservationDate() {
        return observationDate;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getStationNumber() {
        return stationNumber;
    }

    public String getWeatherStationName() {
        return weatherStationName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCompleteDate(LocalDateTime completeDate) {
        this.completeDate = completeDate;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setObservationDate(LocalDateTime observationDate) {
        this.observationDate = observationDate;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public void setPressureMax(double pressureMax) {
        this.pressureMax = pressureMax;
    }

    public void setPressureMin(double pressureMin) {
        this.pressureMin = pressureMin;
    }

    public void setRadiation(double radiation) {
        this.radiation = radiation;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setStationNumber(String stationNumber) {
        this.stationNumber = stationNumber;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setWeatherStationId(int weatherStationId) {
        this.weatherStationId = weatherStationId;
    }

    public void setWeatherStationName(String weatherStationName) {
        this.weatherStationName = weatherStationName;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
