package RestAssured.ApiAutomation.com.rest;

import org.testng.annotations.Test;

import java.util.Collections;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersUseCase {

    /**
     * Hamcrest Matchers using contains() method
     * Hamcrest Matchers using containsInAnyOrder() method
     * Hamcrest Matchers using hasItem() method
     * Hamcrest Matchers using hasItems() method
     * Hamcrest Matchers using empty() method
     * Hamcrest Matchers using emptyArray() method
     * Hamcrest Matchers using hasSize() method
     * Hamcrest Matchers using hasKey() method
     * Hamcrest Matchers using hasValue() method
     * Hamcrest Matchers using hasEntry() method
     * Hamcrest Matchers using equalTo(Collections.EMPTY_MAP) method
     * Hamcrest Matchers using allOf method
     */
    @Test
    public void hamcrestMatchersUseCaseOne() {
        given().
                baseUri("https://api.postman.com").
                header("Enter API Key", "Enter API Key-value").
                when().
                get("/workspaces").
                then().
                log().all().
                assertThat().
                statusCode(200).
                body("workspaces.name", contains("My Workspace", "Ecomm Workspace", "HealthCare Workspace")).
                body("workspaces.name", containsInAnyOrder("HealthCare Workspace", "Ecomm Workspace", "My Workspace")).
                body("workspaces.name", hasItem("Ecomm Workspace")).
                body("workspaces.name", hasItems("Ecomm Workspace", "My Workspace")).
                body("workspaces.name", is(not(empty()))).
                body("workspaces.name", is(not(emptyArray()))).
                body("workspaces.name", hasSize(3)).
                body("workspaces[0]", hasKey("id")).
                body("workspaces[0]", hasValue("My Workspace")).
                body("workspaces[0]", hasEntry("name", "My Workspace")).
                body("workspaces[0]", not(equalTo(Collections.EMPTY_MAP))).
                body("workspaces[0].name", allOf(startsWith("My"), containsString("Workspace")));
    }

}
