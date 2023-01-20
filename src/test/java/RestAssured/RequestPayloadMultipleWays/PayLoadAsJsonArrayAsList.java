package RestAssured.RequestPayloadMultipleWays;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class PayLoadAsJsonArrayAsList {

    String petStoreUrl = "https://petstore.swagger.io/v2/user/";

    @BeforeTest
    public void beforeTest() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri(petStoreUrl)
                .addHeader("Content-Type", "application/json")
                .log(LogDetail.ALL);

        RestAssured.requestSpecification = requestSpecBuilder.build();


        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder()
                .log(LogDetail.ALL);

        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    /**
     *  Map can represent a JSON object and List can represent a JSON array
     */

    @Test
    public void validate_Post_Request_BDD_Style() {
        HashMap<String, String> userOne = new HashMap<String, String>();
        userOne.put("firstName", "Vinu27");
        userOne.put("lastName", "test27");

        HashMap<String, String> userTwo = new HashMap<String, String>();
        userTwo.put("firstName", "Vinu28");
        userTwo.put("lastName", "test28");

        List<HashMap<String,String>> requestMap = new ArrayList<HashMap<String,String>>();
        requestMap.add(userOne);
        requestMap.add(userTwo);

        Response response = given().body(requestMap).
                when().post("createWithArray").
                then().extract().response();
        assertThat(response.statusCode(), is(equalTo(200)));
        assertThat(response.path("message").toString(), is(equalTo("ok")));
    }
}
