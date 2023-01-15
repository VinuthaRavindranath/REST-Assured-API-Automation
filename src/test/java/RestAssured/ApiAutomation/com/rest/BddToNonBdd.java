package RestAssured.ApiAutomation.com.rest;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class BddToNonBdd {
    RequestSpecification requestSpecification;
    String gorestUrl = "https://gorest.co.in/public/v2";

    @BeforeTest
    public void beforeTest() {
        requestSpecification = with().
                baseUri(gorestUrl).
                header("Content-Type", "application/json");
    }

    /**
     * the below is an example of BDD using given() when() and then()
     */

    @Test
    public void requestSpecExampleOne() {
        given(requestSpecification).
                log().all().
                when().
                get("/users").
                then().
                log().all().
                assertThat().statusCode(200);
    }

    /**
     * The below is an example of Non-BDD
     */
    @Test
    public void requestSpecExampleTwo() {
        Response response =requestSpecification.get("/users");
        assertThat(response.statusCode(), is(equalTo(200)));
    }

    /**
     * The below is an example of Non-BDD on how to extract the response body
     */
    @Test
    public void requestSpecExampleThree() {
        Response response =requestSpecification.get("/users").then().log().all().extract().response();
        assertThat(response.statusCode(), is(equalTo(200)));
    }

    /**
     * The below is an example of Non-BDD on how to assert the response body using path()
     */
    @Test
    public void requestSpecExampleFour() {
        Response response =requestSpecification.get("/users/3691").then().log().all().extract().response();
        assertThat(response.statusCode(), is(equalTo(200)));
        assertThat(response.path("id").toString(), is(equalTo(String.valueOf(3691))));
    }



}
