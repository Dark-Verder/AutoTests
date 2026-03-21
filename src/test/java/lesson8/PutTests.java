package lesson8;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PutTests {

    @Test
    public void putRequestTest() {
        RestAssured.baseURI = "https://postman-echo.com";

        String requestBody = """
                {
                  "name": "Jhon",
                  "age": 30
                }
                """;

        given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .body("data.name", equalTo("Jhon"))
                .body("data.age", equalTo(30));
    }
}