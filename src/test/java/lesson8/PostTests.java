package lesson8;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;

public class PostTests {

    @Test
    public void postRawTextTest() {
        RestAssured.baseURI = "https://postman-echo.com";

        given()
                .contentType("text/plain")
                .body("Hello from Postman")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", containsString("Hello from Postman"))
                .body("data", notNullValue())
                .body("data", instanceOf(String.class))
                .body("url", containsString("/post"));
    }
}
