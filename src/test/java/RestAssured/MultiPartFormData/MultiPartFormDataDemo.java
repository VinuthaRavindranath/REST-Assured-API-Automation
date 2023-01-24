package RestAssured.MultiPartFormData;

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

public class MultiPartFormDataDemo {
    String petStoreUrl = "https://petstore.swagger.io/v2/";

    @BeforeTest
    public void beforeTest() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri(petStoreUrl)
              // .addHeader("Content-Type", "application/json")
                .log(LogDetail.ALL);

        RestAssured.requestSpecification = requestSpecBuilder.build();


        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder()
                .log(LogDetail.ALL);

        RestAssured.responseSpecification = responseSpecBuilder.build();
    }


    @Test
    public void validate_Post_Request_BDD_Style() {
       File file =new File("/Users/vinuthar/Desktop/APIAutomationRestAssured/Rest-Assured-Api-Automation/src/test/java/RestAssured/MultiPartFormData/ApiDemoImage.png");


        Response response = given().
                multiPart("additionalMetadata","test").
                       multiPart("file",file).
                when().post("pet/2719/uploadImage").
                then().extract().response();
        assertThat(response.statusCode(), is(equalTo(200)));
    }
}
