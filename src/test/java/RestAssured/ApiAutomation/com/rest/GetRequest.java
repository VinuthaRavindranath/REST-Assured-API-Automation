package RestAssured.ApiAutomation.com.rest;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetRequest {

    @Test
    public void validateGetRequestStatusCode() {
        given().
                baseUri("https://api.postman.com").
                header("Enter API Key", "Enter API Key-value").
                when().
                get("/workspaces").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void validateGetRequestResponseBody() {
        given().
                baseUri("https://api.postman.com").
                header("Enter API Key", "Enter API Key-value").
                when().
                get("/workspaces").
                then().
                log().all().
                assertThat().
                statusCode(200).
                body("workspaces.name", hasItem("My Workspace"),
                        "workspaces.type", hasItem("personal"),
                        "workspaces[0].visibility", is(equalTo("personal")),
                        "workspaces.size", is(equalTo(3)));
    }

    @Test
    public void extractCompleteResponse() {
        Response res = given().
                baseUri("https://api.postman.com").
                header("Enter API Key", "Enter API Key-value").
                when().
                get("/workspaces").
                then().
                log().all().
                assertThat().
                statusCode(200).
                extract().response();
        System.out.println("Response: " + res.asString());
    }

    /**
     * There are multiple ways ti extract value from the response
     * below is the first way to extract:
     */
    @Test
    public void extractSingleValueFromResponseWayOne() {
        Response res = given().
                baseUri("https://api.postman.com").
                header("Enter API Key", "Enter API Key-value").
                when().
                get("/workspaces").
                then().
                log().all().
                assertThat().
                statusCode(200).
                extract().response();
        System.out.println("Workspace Name: " + res.path("workspaces[0].name"));
    }

    /**
     * below is the second way to extract:
     */
    @Test
    public void extractSingleValueFromResponseWayTwo() {
        Response res = given().
                baseUri("https://api.postman.com").
                header("Enter API Key", "Enter API Key-value").
                when().
                get("/workspaces").
                then().
                log().all().
                assertThat().
                statusCode(200).
                extract().response();
        JsonPath jsonPath = new JsonPath(res.asString());
        System.out.println("Workspace Name: " + jsonPath.getString("workspaces[0].name"));
    }

    /**
     * below is the third way to extract:
     */
    @Test
    public void extractSingleValueFromResponseWayThree() {
        String res = given().
                baseUri("https://api.postman.com").
                header("Enter API Key", "Enter API Key-value").
                when().
                get("/workspaces").
                then().
                log().all().
                assertThat().
                statusCode(200).
                extract().response().asString();
        System.out.println("Workspace Name: " + JsonPath.from(res).getString("workspaces[0].name"));
    }

    /**
     * below is the fourth way to extract:
     */
    @Test
    public void extractSingleValueFromResponseWayFour() {
        String res = given().
                baseUri("https://api.postman.com").
                header("Enter API Key", "Enter API Key-value").
                when().
                get("/workspaces").
                then().
                log().all().
                assertThat().
                statusCode(200).
                extract().response().path("workspaces[0].name");
        assertThat(res, is(equalTo("My Workspace")));
    }
}
