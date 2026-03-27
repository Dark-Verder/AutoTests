package lesson8;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class GetTests {

    @Test
    void getRequestTest() {

        Response response = given()
                .queryParam("city", "London")
                .queryParam("street", "Primavera")
                .when()
                .get("https://postman-echo.com/get");

        response.then().statusCode(200);

        response.then().body("args.city", equalTo("London"));
        response.then().body("args.street", equalTo("Primavera"));

        response.then().body("url", containsString("/get"));
        response.then().body("url", containsString("city=London"));
        response.then().body("url", containsString("street=Primavera"));
    }
}

