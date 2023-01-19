package RestAssured.Basics;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ResponseSpecificationBuilderDemo {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    String gorestUrl = "https://gorest.co.in/public/v2";

    @BeforeTest
    public void beforeTest() {
        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
        reqBuilder.setBaseUri(gorestUrl);
        reqBuilder.addHeader("Content-Type", "application/json");
        reqBuilder.log(LogDetail.ALL);
        requestSpecification =reqBuilder.build();

        ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
        resBuilder.expectStatusCode(200);
        resBuilder.log(LogDetail.ALL);
        responseSpecification =resBuilder.build();
    }

    @Test
    public void requestSpecExampleOne() {
        Response response =given().spec(requestSpecification).get("/users/3633").
                then().spec(responseSpecification).extract().response();
        assertThat(response.path("id").toString(), is(equalTo(String.valueOf(3633))));
    }
}
