package lesson8;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;

public class PostFormDataTest {

    @Test
    public void postFormDataTest() {
        RestAssured.baseURI = "https://postman-echo.com";

        given()
                .multiPart("name", "Manuel")
                .multiPart("address", "5, Primavera street")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("form.name", equalTo("Manuel"))
                .body("form.address", equalTo("5, Primavera street"))
                .body("form.name", notNullValue())
                .body("form.address", notNullValue())
                .body("form.name", instanceOf(String.class))
                .body("form.address", instanceOf(String.class))
                .body("url", containsString("/post"));
    }
}

