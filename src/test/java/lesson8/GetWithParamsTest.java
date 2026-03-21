package lesson8;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetWithParamsTest {

    @Test
    public void getRequestWithParamsTest() {
        RestAssured.baseURI = "https://postman-echo.com";

        given()
                .queryParam("greeting", "Hello")
                .queryParam("name", "Vader")
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .body("args.greeting", equalTo("Hello"))
                .body("args.name", equalTo("Vader"))
                .body("args.greeting", notNullValue())
                .body("args.greeting", instanceOf(String.class))
                .body("url", containsString("/get"))
                .body("url", containsString("greeting=Hello"))
                .body("url", containsString("name=Vader"));
    }
}

