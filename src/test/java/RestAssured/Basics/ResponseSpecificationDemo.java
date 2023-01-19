package RestAssured.Basics;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class ResponseSpecificationDemo {
    String gorestUrl = "https://gorest.co.in/public/v2";

    /**
     * By using ResponseSpecification interface reference
     * responseSpecification reference should be passed to then().spec(responseSpecification)
     */
    @Test
    public void requestSpecExampleOne() {
        RequestSpecification requestSpecification = given().
                baseUri(gorestUrl).
                header("Content-Type", "application/json");

        ResponseSpecification responseSpecification = RestAssured.expect().
                statusCode(200).
                log().all();


        given(requestSpecification).
                log().all().
                when().
                get("/users").
                then().spec(responseSpecification);
    }

}
