package RestAssured.Basics;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

public class ReuseRequestSpecification {

    RequestSpecification requestSpecification;
    String gorestUrl = "https://gorest.co.in/public/v2";

    @BeforeTest
    public void beforeTest() {
        requestSpecification = with().
                baseUri(gorestUrl).
                header("Content-Type", "application/json");
    }


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
    @Test
    public void requestSpecExampleTwo() {
        given(requestSpecification).
                log().all().
                when().
                get("/users/3697").
                then().
                log().all().
                assertThat().statusCode(200);
    }
}
