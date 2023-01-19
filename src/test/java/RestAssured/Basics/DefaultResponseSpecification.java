package RestAssured.Basics;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class DefaultResponseSpecification {
    String gorestUrl = "https://gorest.co.in/public/v2";


    @BeforeTest
    public void beforeTest() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(gorestUrl);
        builder.addHeader("Content-Type", "application/json");
        builder.log(LogDetail.ALL);
        RestAssured.requestSpecification = builder.build();

        ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
        resBuilder.expectStatusCode(200);
        resBuilder.log(LogDetail.ALL);
        RestAssured.responseSpecification = resBuilder.build();
    }

    @Test
    public void requestSpecExampleOne() {
        Response response = get("/users/3633").
                then().extract().response();
        assertThat(response.path("id").toString(), is(equalTo(String.valueOf(3633))));
    }
}
