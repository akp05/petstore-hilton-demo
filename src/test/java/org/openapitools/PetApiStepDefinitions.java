package org.openapitools;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PetApiStepDefinitions {
    private Response response;

    @LocalServerPort
    private int port;

    @Given("a pet with ID {int} exists")
    public void a_pet_with_id_exists(int petId) {
        // Optionally, create a pet here if needed
    }

    @When("I request the pet by ID {int}")
    public void i_request_the_pet_by_id(int petId) {
        response = given()
            .baseUri("http://localhost:" + port + "/v2")
            .when()
            .get("/pet/" + petId);
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response should contain the pet details")
    public void the_response_should_contain_the_pet_details() {
        response.then().body("id", notNullValue());
        response.then().body("name", notNullValue());
    }
} 