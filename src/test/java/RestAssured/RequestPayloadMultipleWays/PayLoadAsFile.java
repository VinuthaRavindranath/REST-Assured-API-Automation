package RestAssured.RequestPayloadMultipleWays;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class PayLoadAsFile {

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
    public void validate_Post_Request_BDD_Style() {
        File file = new File(System.getProperty("user.dir") +"/src/main/java/Resources/PostRequestPayload.json");

        Response response = given().body(file).
                when().post("users/").
                then().extract().response();
        assertThat(response.statusCode(), is(equalTo(201)));
        assertThat(response.path("name").toString(), is(equalTo("VinuApi19thJanUser2")));
    }
}
