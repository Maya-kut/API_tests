package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ReqresInTests extends TestBase {
    @Test
    void successfulCreateUserTest() {
        String newUser = "{\n" +
                "    \"name\": \"Hercules\",\n" +
                "    \"job\": \"God\"\n" +
                "}";

        given()
                .header("x-api-key", apiKey)
                .body(newUser)
                .contentType(JSON)
                .log().uri()
                .when()
                .post("users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("Hercules"))
                .body("job", is("God"));

    }

    @Test
    void successfulUpdateUserTest() {
        Integer userId = 2;
        String newUserInfo = "{\n" +
                "    \"name\": \"Hercules\",\n" +
                "    \"job\": \"God\"\n" +
                "}";

        given()
                .header("x-api-key", apiKey)
                .body(newUserInfo)
                .contentType(JSON)
                .log().uri()
                .when()
                .post(("users" + "/" + userId))
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("Hercules"))
                .body("job", is("God"));

    }

    @Test
    void unSuccessfulRegisterUserTest() {
        String logoPass = "{\"email\": \"sydney@fife\"}";

        given()
                .header("x-api-key", apiKey)
                .body(logoPass)
                .contentType(JSON)
                .log().uri()
                .when()
                .post("register")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));

    }

    @Test
    void getSingleUserTest() {
        Integer userId = 2;
        String userEmail = "janet.weaver@reqres.in";
        given()
                .header("x-api-key", apiKey)
                .log().uri()
                .when()
                .get("users" + "/" + userId)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", is(userId))
                .body("data.email", is(userEmail));

    }

    @Test
    void deleteUserTest() {
        Integer userId = 2;
        given()
                .header("x-api-key", apiKey)
                .log().uri()
                .when()
                .delete("users" + "/" + userId)
                .then()
                .log().status()
                .statusCode(204);
    }
    }

