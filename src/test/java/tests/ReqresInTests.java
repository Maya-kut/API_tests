package tests;

import models.*;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.ReqresInTestsSpecs.responseSpecification;

public class ReqresInTests extends TestBase {
    int UserId = 2;

    @Test
    void successfulCreateUserTest() {
        CreateUpdateUserBodyModel createData = new CreateUpdateUserBodyModel();
        createData.setName("Hercules");
        createData.setJob("God");

        CreateUpdateUserResponseModel response = step("Make request for create user", () ->

                given(requestSpecification)
                        .body(createData)

                        .when()
                        .post(userEndpoint)

                        .then()
                        .spec(responseSpecification(200))
                        .extract().as(CreateUpdateUserResponseModel.class));

        step("Check name in response body", () ->
                assertThat(response.getName())
                        .as("Check name")
                        .isEqualTo(createData.getName()));
        step("Check job in response body", () ->
                assertThat(response.getName())
                        .as("Check job")
                        .isEqualTo(createData.getJob()));
    }

    @Test
    void successfulUpdateUserTest() {
        CreateUpdateUserBodyModel putData = new CreateUpdateUserBodyModel();
        putData.setName("Zeus");
        putData.setJob("DadOfGod");

        CreateUpdateUserResponseModel response = step("Make request for update user", () ->
                given(requestSpecification)
                        .body(putData)

                        .when()
                        .post(userEndpoint)

                        .then()
                        .spec(responseSpecification(200))
                        .extract().as(CreateUpdateUserResponseModel.class));

        step("Check name in response body", () ->
                assertThat(response.getName())
                        .as("Check name")
                        .isEqualTo(putData.getName()));
        step("Check job in response body", () ->
                assertThat(response.getName())
                        .as("Check job")
                        .isEqualTo(putData.getJob()));
    }

    @Test
    void unSuccessfulRegisterUserTest() {
        RegisterUserBodyModel registerData = new RegisterUserBodyModel();
        registerData.setPassword("hercules");
        UnsuccessRegisterUserResponseModel response = step("Make request", () ->
                given(requestSpecification)
                        .body(registerData)
                        .post(registerEndpoint)
                        .then()
                        .spec(responseSpecification(400))
                        .extract().as(UnsuccessRegisterUserResponseModel.class));
        step("Check response", () ->
                assertThat(response.getError())
                        .as("Check error message for missing email")
                        .isEqualTo("Missing email or username")
        );
    }

//    @Test
//    void getSingleUserTest() {
//        SingleUserResponseModel userResponse = step("Make request for get user", () ->
//                given(requestSpecification)
//                        .pathParam("userId", UserId)
//                        .when()
//                        .get(userEndpoint + "{userId}")
//
//                        .then()
//                        .spec(responseSpecification(200))
//                        .extract().as(SingleUserResponseModel.class));
//        step("Check user information in response", () -> {
//            assertThat(userResponse.getInformation().getId()).isEqualTo(2);
//            assertThat(userResponse.getInformation().getEmail())
//                    .as("Email should contain @ and . symbols")
//                    .contains("@")
//                    .contains(".");
//        });
//    }

    @Test
    void deleteUserTest() {
        step("Make request for delete user and check status code", () ->
                given(requestSpecification)
                        .pathParam("userId", UserId)
                        .when()
                        .delete(userEndpoint + "{userId}")

                        .then()
                        .spec(responseSpecification(204)));
    }
}

