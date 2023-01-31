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

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class LogToFile {
    String petStoreUrl = "https://petstore.swagger.io/v2/";

    /**
     * Commented the log(LogDetail.ALL) to use filter method
     */

    @BeforeTest
    public void beforeTest() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri(petStoreUrl)
                .addHeader("Content-Type", "application/json");
        //   .log(LogDetail.ALL);

        RestAssured.requestSpecification = requestSpecBuilder.build();


        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        //.log(LogDetail.ALL);

        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    /**
     * filter(new RequestLoggingFilter()). and filter(new ResponseLoggingFilter()) is used to log request and response
     * in-case if we need only specific request header, cookies then we can filter(new RequestLoggingFilter(LogDetail.HEADERS))
     */

    @Test
    public void validate_Post_Request_BDD_Style() throws FileNotFoundException {
        PrintStream fileOutputStream = new PrintStream(new File("restAssured.log"));

        File file = new File(System.getProperty("user.dir") + "/src/main/java/Resources/PetPostRequestPayload.json");
        given().
//                filter(new RequestLoggingFilter(fileOutputStream)).
//                filter(new ResponseLoggingFilter(fileOutputStream)).
                filter(new RequestLoggingFilter(LogDetail.HEADERS, fileOutputStream)).
                filter(new ResponseLoggingFilter(LogDetail.STATUS, fileOutputStream)).
                body(file).
                when().post("pet").
                then().assertThat().body(matchesJsonSchemaInClasspath(("PetResponseSchema.json")));
    }
}
