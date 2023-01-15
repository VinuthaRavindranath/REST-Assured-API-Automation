package RestAssured.ApiAutomation.com.rest;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class AutomateHeaders {
    String gorestUrl = "https://gorest.co.in/public/v2";
    @Test
    public void automateHeadersTestUseCaseOne() {
        given().
                baseUri(gorestUrl).
                header("Content-Type", "application/json").
                header("Authorization", "Bearer ACCESS-TOKEN").
                log().all().
                when().
                get("/users").
                then().
                log().all().
                assertThat().statusCode(200);
    }

    /**
     * By using Header class
     */
    @Test
    public void automateHeadersTestUseCaseTwo() {
        Header contentTypes = new Header("Content-Type", "application/json");
        Header auth = new Header("Authorization", "Bearer ACCESS-TOKEN");
        given().
                baseUri(gorestUrl).
                header("Content-Type", "application/json").
                header("Authorization", "Bearer ACCESS-TOKEN").
                log().all().
                when().
                get("/users").
                then().
                log().all().
                assertThat().statusCode(200);
    }

    /**
     * By using Headers class
     */
    @Test
    public void automateHeadersTestUseCaseThree() {
        Header contentTypes = new Header("Content-Type", "application/json");
        Header auth = new Header("Authorization", "Bearer ACCESS-TOKEN");

        Headers headers = new Headers(contentTypes, auth);

        given().
                baseUri(gorestUrl).
                headers(headers).
                log().all().
                when().
                get("/users").
                then().
                log().all().
                assertThat().statusCode(200);
    }

    /**
     * By using HashMap
     */
    @Test
    public void automateHeadersTestUseCaseFour() {
        HashMap<String, String> headers = new HashMap<String,String>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer ACCESS-TOKEN");

        given().
                baseUri(gorestUrl).
                headers(headers).
                log().all().
                when().
                get("/users").
                then().
                log().all().
                assertThat().statusCode(200);
    }

    /**
     * Automate Response headers
     */
    @Test
    public void automateResponseHeaders() {
        HashMap<String, String> reqHeaders = new HashMap<String,String>();
        reqHeaders.put("Content-Type", "application/json");
        reqHeaders.put("Authorization", "Bearer ACCESS-TOKEN");

        HashMap<String, String> resHeaders = new HashMap<String,String>();
        resHeaders.put("x-pagination-page","1");
        resHeaders.put("x-pagination-limit","10");

        given().
                baseUri(gorestUrl).
                headers(reqHeaders).
                log().all().
                when().
                get("/users").
                then().
                log().all().
                headers(resHeaders).
                assertThat().statusCode(200);
    }
}
