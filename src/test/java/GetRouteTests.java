import domain.Weather;
import service.WeatherService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import web.ApplicationRoutes;
import web.WeatherHandler;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class GetRouteTests {

    private WebTestClient client;
    private WeatherService weatherService;

    private UUID sampleUUID = UUID.fromString("49f8a310-0159-11ea-b963-3d0a1f9c9088");

    @Before
    public void setUp() {
        this.weatherService = mock(WeatherService.class);
        when(weatherService.findOne(sampleUUID)).thenReturn(Mono.just(new Weather(sampleUUID, "test", LocalDateTime.now(),22.05d)));
        WeatherHandler weatherHandler = new WeatherHandler(weatherService);
        
        this.client = WebTestClient.bindToRouterFunction(ApplicationRoutes.routes(weatherHandler)).build();
    }


    @Test
    public void testWeatherGet() throws Exception {
        this.client.get().uri("/weathers/" + sampleUUID)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Weather.class)
                .isEqualTo(new Weather(sampleUUID, "test",LocalDateTime.now(),22.05d));
    }
}
