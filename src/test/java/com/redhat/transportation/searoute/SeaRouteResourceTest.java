package com.redhat.transportation.searoute;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class SeaRouteResourceTest {

    @Test
    public void testRouteEndpoint() {
        given()
                .param("startLat", 43.3)
                .param("startLon", 5.3)
                .param("endLat", 31.2)
                .param("endLat", 121.8)
                .when().get("/route")
                .then()
                .statusCode(200)
                .body(is("Hello RESTEasy"));
    }

}