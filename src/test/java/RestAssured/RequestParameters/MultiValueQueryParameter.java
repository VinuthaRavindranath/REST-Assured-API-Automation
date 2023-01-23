package RestAssured.RequestParameters;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class MultiValueQueryParameter {
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
    public void getRequestWithMultipleValueQueryParameter() {
        given().
                queryParam("page","1,2").
                log().all().
                when().
                get("users").
                then().
                log().all().
                assertThat().statusCode(200);
    }
}
