package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {


    public static String apiKey = "reqres-free-v1";
    public static String registerEndpoint = "/register";
    public static String userEndpoint = "/users/";

    @BeforeAll
    static void beforeAll() {

        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api/";
    }
}
