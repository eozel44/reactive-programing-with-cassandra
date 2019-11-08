package web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


public interface ApplicationRoutes {
    static RouterFunction<?> routes(WeatherHandler weatherHandler) {
        return nest(path("/weathers"),
                nest(accept(MediaType.APPLICATION_JSON),
                        route(GET("/{id}"), weatherHandler::get)
                                .andRoute(POST("/"), weatherHandler::save)
                                .andRoute(PUT("/"), weatherHandler::update)
                                .andRoute(DELETE("/{id}"), weatherHandler::delete)
                                .andRoute(GET("/fromcity/{city}"), weatherHandler::findWeatherInCity)
                ));
    }
}
