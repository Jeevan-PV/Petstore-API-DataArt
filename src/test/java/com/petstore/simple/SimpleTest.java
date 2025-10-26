package com.petstore.simple;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SimpleTest {
	@Test
    public void testGetPetById() {
        // Set the base URI directly in the test (for this simple example)
        String BASE_URI = "https://petstore.swagger.io/v2";

        given()
            .baseUri(BASE_URI)
            .pathParam("petId", 10) // Using pet/1 as an example
        .when()
            .get("/pet/{petId}")
        .then()
            .statusCode(200) // Assert HTTP status code is 200 (OK)
            .body("id", equalTo(10)) // Assert the 'id' field in the JSON response
            .body("status", notNullValue()) // Assert 'status' is not null
            .log().all(); // Print the request and response details
        }		
}
