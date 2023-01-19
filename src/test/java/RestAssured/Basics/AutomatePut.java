package RestAssured.Basics;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class AutomatePut {

    String restfulBooker = "https://gorest.co.in/public/v2/";
    String authToken = "Bearer Token";
    String userId = "69619";


    @BeforeTest
    public void beforeTest() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(restfulBooker);
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
    public void validate_Put_Request_BDD_Style() {
        String payload = "{\n" +
                "    \"name\": \"VinuApi8test\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"status\": \"active\"\n" +
                "}";
        Response response = given().body(payload).
                pathParam("userId", userId).
                when().put("users/{userId}").
                then().extract().response();
        assertThat(response.path("name").toString(), is(equalTo("VinuApi8test")));
        assertThat(response.statusCode(), is(equalTo(200)));
    }
}
