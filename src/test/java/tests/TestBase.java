package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    public String registerEndpoint = "/register";
    public String apiKey = "reqres-free-v1";
    public String usersEndpoint = "/users/";

    @BeforeAll
    static void beforeAll() {

        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }
}
