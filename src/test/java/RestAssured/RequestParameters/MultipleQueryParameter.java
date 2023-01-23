package RestAssured.RequestParameters;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class MultipleQueryParameter {
    String gorestUrl = "https://gorest.co.in/public/v2/";
    String authToken = "Bearer Token";


    @BeforeTest
    public void beforeTest() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(gorestUrl);
        builder.addHeader("Content-Type", "application/json");
        builder.addHeader("Authorization", authToken);
        builder.log(LogDetail.ALL);
        RestAssured.requestSpecification = builder.build();

        ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
        // resBuilder.expectStatusCode(200);
        resBuilder.log(LogDetail.ALL);
        RestAssured.responseSpecification = resBuilder.build();
    }

    @Test
    public void getRequestWithMultipleQueryParameterUseCaseOne() {
        given().
                queryParam("page",1).
                queryParam("per_page",30).
                log().all().
                when().
                get("users").
                then().
                log().all().
                assertThat().statusCode(200);
    }

    @Test
    public void getRequestWithMultipleQueryParameterUseCaseTwo() {
        HashMap<String, Integer> params = new HashMap<String, Integer>();
        params.put("page", 1);
        params.put("per_page", 30);
        given().
               queryParams(params).
                log().all().
                when().
                get("users").
                then().
                log().all().
                assertThat().statusCode(200);
    }

}
