package com.petstore.api.tests;

import com.mycompany.api.payload.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserCRUDTest {

    private String BASE_URI = "https://petstore.swagger.io/v2";
    private String username = "JeevanTest" + (int)(Math.random() * 100000);
    private User userPayload;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URI;
        
        // 1. Prepare the Payload using the POJO
        userPayload = new User();
        userPayload.setId(101);
        userPayload.setUsername(username);
        userPayload.setFirstName("Test");
        userPayload.setLastName("User");
        userPayload.setEmail(username + "@test.com");
        userPayload.setPassword("password123");
        userPayload.setPhone("1234567890");
        userPayload.setUserStatus(1);
    }

    @Test(priority = 1)
    public void testCreateUser_POST() {
        System.out.println("--- Executing POST Create User ---");
        
        // 2. Perform POST Request
        Response response = given()
            .contentType(ContentType.JSON)
            .body(userPayload) // RestAssured converts POJO to JSON
        .when()
            .post("/user");

        // 3. Assertions (JSON Parsing and Status Check)
        response.then()
            .statusCode(200) // Successful POST usually returns 200 on this API
            .body("code", equalTo(200))
            .body("message", containsString(String.valueOf(userPayload.getId())))
            .log().all(); 

        // Optional: Ensure the username is correctly set in the response (if available)
        // Assert.assertEquals(response.jsonPath().getString("message"), String.valueOf(userPayload.getId()));
    }

    @Test(priority = 2)
    public void testReadUser_GET() {
        System.out.println("--- Executing GET Read User ---");

        // 4. Perform GET Request
        Response response = given()
            .pathParam("username", username)
        .when()
            .get("/user/{username}");

        // 5. Assertions (JSON Parsing)
        response.then()
            .statusCode(200) // Assert status code
            .body("username", equalTo(username)) // Assert created username
            .body("firstName", equalTo(userPayload.getFirstName())) // Assert other fields
            .log().all(); 
        
        // Example of explicit JSON parsing and TestNG assertion
        String returnedEmail = response.jsonPath().getString("email");
        Assert.assertEquals(returnedEmail, userPayload.getEmail(), "Email mismatch after GET");
    }

    @Test(priority = 3)
    public void testUpdateUser_PUT() {
        System.out.println("--- Executing PUT Update User ---");
        String newFirstName = "UpdatedTest";
        
        // 6. Update the Payload
        userPayload.setFirstName(newFirstName);
        userPayload.setUserStatus(2); // New status
        
        // 7. Perform PUT Request
        given()
            .contentType(ContentType.JSON)
            .pathParam("username", username)
            .body(userPayload)
        .when()
            .put("/user/{username}")
        .then()
            .statusCode(200) 
            .log().all();
        
    }
    
    @Test(priority = 4)
    public void testReadUserAfterUpdate_GET() {
        System.out.println("--- Executing GET Read User After Update ---");
        // Use the same GET logic
        Response response = given()
            .pathParam("username", username)
        .when()
            .get("/user/{username}");

        // Assertions for the updated state
        response.then()
            .statusCode(200) // Expect 200 after successful update
            .body("username", equalTo(username))
            .body("firstName", equalTo(userPayload.getFirstName())) // Check updated first name
            //.body("userStatus", equalTo(2)) // Check updated status
            .log().all();
    }

    @Test(priority = 5)
    public void testDeleteUser_DELETE() {
        System.out.println("--- Executing DELETE User ---");

        // 9. Perform DELETE Request
        given()
            .pathParam("username", username)
        .when()
            .delete("/user/{username}")
        .then()
            .statusCode(200) // Assert status code
            .log().all();
    }
}