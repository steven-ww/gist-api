package za.co.ee;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.quarkiverse.wiremock.devservice.InjectWireMock;
import io.quarkiverse.wiremock.devservice.WireMockServerTestResource;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@QuarkusTest
@QuarkusTestResource(WireMockServerTestResource.class)
public class GistResourceTest {

    // Todo add tests for paging and page size. Also test retries, timeouts and exceptions
    @InjectWireMock
    WireMock wiremock;

    String jsonString = """
            [{
                "url": "https://api.github.com/gists/6cad326836d38bd3a7ae",
                "files": {
                    "hello_world.rb": {
                        "filename": "hello_world.rb",
                            "type": "application/x-ruby",
                            "language": "Ruby",
                            "size": 175
                    }
                },
                "public": true,
                "owner": {
                    "login": "octocat",
                    "id": 583231
                },
                "truncated": false
            }]
    """;

    @Test
    public void testGetGistsEndpoint() {
        wiremock.register(get(urlEqualTo("/users/octocat/gists"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(jsonString)));

        given()
          .when().get("/octocat")
          .then()
             .statusCode(200)
             .body("[0].url", equalTo("https://api.github.com/gists/6cad326836d38bd3a7ae"))
             .body("[0].owner.login", equalTo("octocat"));
    }

}