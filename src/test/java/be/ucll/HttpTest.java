package be.ucll;

import be.ucll.repository.PonyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
@AutoConfigureWebTestClient
public class HttpTest {

    @Autowired
    private WebTestClient client;

    @Autowired
    private PonyRepository repository;

    @AfterEach
    public void resetData() {
        repository.resetRepositoryData();
    }

    @Test
    public void given3Ponies_whenInvokingGetPony_then3PoniesAreReturned() {
        client.get()
                .uri("/pony")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .json("[{\"name\":\"Bella\",\"age\":5},{\"name\":\"Luna\",\"age\":7},{\"name\":\"Angel\",\"age\":12}]");
    }

    @Test
    public void givenNoPonyTornado_whenInvokingPostTornado_thenPonyTornadoIsSaved() {
        client.post()
                .uri("/pony")
                .header("Content-Type", "application/json")
                .bodyValue("{\"name\":\"Tornado\",\"age\":17}")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .json("{\"name\":\"Tornado\",\"age\":17}");

        Assertions.assertEquals ("Tornado", repository.findPonyByName("Tornado").getName());
    }

    @Test
    public void givenPonyBella_whenInvokingDeleteBella_thenPonyBellaIsRemoved() {
        client.delete()
                .uri("/pony/Bella")
                .exchange()
                .expectStatus().is2xxSuccessful();

        Assertions.assertThrows (IllegalArgumentException.class, () -> repository.findPonyByName("Bella"));
    }

    @Test
    public void givenPonyBella_whenInvokingUpdateBella_thenNewInformationIsSavedInDb() {
        client.put()
                .uri("/pony/Bella")
                .header("Content-Type", "application/json")
                .bodyValue("{\"name\":\"TheNicest\",\"age\":17}")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .json("{\"name\":\"TheNicest\",\"age\":17}");

        Assertions.assertThrows (IllegalArgumentException.class, () -> repository.findPonyByName("Bella"));

        Assertions.assertEquals ("TheNicest", repository.findPonyByName("TheNicest").getName());
    }
}