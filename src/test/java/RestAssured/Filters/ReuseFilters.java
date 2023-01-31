package RestAssured.Filters;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ReuseFilters {
    String petStoreUrl = "https://petstore.swagger.io/v2/";

    /**
     * Commented the log(LogDetail.ALL) to use filter method
     */

    @BeforeTest
    public void beforeTest() throws FileNotFoundException {
        PrintStream fileOutputStream = new PrintStream(new File("restAssured.log"));
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri(petStoreUrl)
                .addHeader("Content-Type", "application/json").
                addFilter(new RequestLoggingFilter(fileOutputStream)).
                addFilter(new ResponseLoggingFilter(fileOutputStream));
        RestAssured.requestSpecification = requestSpecBuilder.build();


        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    /**
     * filter(new RequestLoggingFilter()). and filter(new ResponseLoggingFilter()) is used to log request and response
     * in-case if we need only specific request header, cookies then we can filter(new RequestLoggingFilter(LogDetail.HEADERS))
     */

    @Test
    public void validate_Post_Request_BDD_Style(){
        File file = new File(System.getProperty("user.dir") + "/src/main/java/Resources/PetPostRequestPayload.json");
        given(requestSpecification).
                body(file).
                when().post("pet").
                then().spec(responseSpecification).assertThat().body(matchesJsonSchemaInClasspath(("PetResponseSchema.json")));
    }
}
