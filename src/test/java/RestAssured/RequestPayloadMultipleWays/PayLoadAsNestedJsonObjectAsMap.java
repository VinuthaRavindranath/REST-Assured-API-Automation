package RestAssured.RequestPayloadMultipleWays;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class PayLoadAsNestedJsonObjectAsMap {

    String gorestUrl = "https://gorest.co.in/public/v2/";
    String authToken = "Bearer Token";

    @BeforeTest
    public void beforeTest(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(gorestUrl);
        requestSpecBuilder.addHeader("Content-Type", "application/json");
        requestSpecBuilder.addHeader("Authorization", authToken);
        requestSpecBuilder.log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();


        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void validate_Post_Request_BDD_Style() {
        HashMap<String, String>  requestMap = new HashMap<String, String>();
        requestMap.put("name","VinuApi19thJanUser4");
        requestMap.put("gender","Female");
        requestMap.put("email","vinuApi19thJanUser4@gmail.com");
        requestMap.put("status","active");


        Response response = given().body(requestMap).
                when().post("users/").
                then().extract().response();
        assertThat(response.statusCode(), is(equalTo(201)));
        assertThat(response.path("name").toString(), is(equalTo("VinuApi19thJanUser4")));
    }

}
