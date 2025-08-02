package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import tests.TestBase;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class ReqresInTestsSpecs extends TestBase {
    public static RequestSpecification requestSpecification = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .log().headers()
            .header("x-api-key", apiKey)
            .contentType(JSON);

    public static ResponseSpecification responseSpecification(int expectedStatusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(expectedStatusCode)
                .log(STATUS)
                .log(BODY)
                .build();
    }
}

