package RestAssured.Basics;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class RequestSpecificationBuilder {
    RequestSpecification requestSpecification;
    String gorestUrl = "https://gorest.co.in/public/v2";

    /**
     * In here I'm using the RequestSpecBuilder class and creating an object
     * using the builder object I'm setting the baseUrl and the headers
     * lastly using build() method and assigning it to requestSpecification Interface
     */

    @BeforeTest
    public void beforeTest() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(gorestUrl);
        builder.addHeader("Content-Type", "application/json");
        builder.log(LogDetail.ALL);
        requestSpecification =builder.build();
    }

    /**
     * When I use the RequestSpecBuilder, I cannot use requestSpecification.get("/users/3691") directly
     * I should use given().spec(requestSpecification).get("/users/3691")
     */

    @Test
    public void requestSpecExampleOne() {
        Response response =given().spec(requestSpecification).get("/users/3691").then().log().all().extract().response();
        assertThat(response.statusCode(), is(equalTo(200)));
        assertThat(response.path("id").toString(), is(equalTo(String.valueOf(3691))));
    }

}
