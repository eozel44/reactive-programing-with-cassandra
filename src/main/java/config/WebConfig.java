package config;


import web.ApplicationRoutes;
import web.WeatherHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;


@Configuration
public class WebConfig {

    @Autowired
    private WeatherHandler weatherHandler;

    @Bean
    public RouterFunction<?> routerFunction() {
        return ApplicationRoutes.routes(this.weatherHandler);
    }
}
