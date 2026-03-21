package lesson8;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PatchTests {

    @Test
    public void patchRequestTest() {
        RestAssured.baseURI = "https://postman-echo.com";

        String requestBody = """
                {
                  "city": "Naples",
                  "age": 2700
                }
                """;

        given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .patch("/patch")
                .then()
                .statusCode(200)
                .body("json.city", equalTo("Naples"))
                .body("json.age", equalTo(2700));
    }
}
