package Checkouts;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredTest {

    @Test
    void simpleGetTest() {
        Response response = given()
                .when()
                .get("https://postman-echo.com/get");

        System.out.println(response.getStatusCode());
    }
}
