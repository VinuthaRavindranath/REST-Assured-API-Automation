package RestAssured.ApiAutomation.com.rest;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RequestSpecificationExample {
    String gorestUrl = "https://gorest.co.in/public/v2";

    /**
     * By using RequestSpecification interface reference
     */
    @Test
    public void requestSpecExampleOne() {
        RequestSpecification requestSpecification = given().
                baseUri(gorestUrl).
                header("Content-Type", "application/json");

        given(requestSpecification).
                log().all().
                when().
                get("/users").
                then().
                log().all().
                assertThat().statusCode(200);
    }


    /**
     * By using RequestSpecification interface reference and spec() method
     */
    @Test
    public void requestSpecExampleTwo() {
        RequestSpecification requestSpecification = given().
                baseUri(gorestUrl).
                header("Content-Type", "application/json");

        given().spec(requestSpecification).
                log().all().
                when().
                get("/users").
                then().
                log().all().
                assertThat().statusCode(200);
    }
}
