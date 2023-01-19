package RestAssured.Basics;

import io.restassured.config.LogConfig;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class AutomateRestAssuredLogging{

    String restfulBookerBaseUrl="https://restful-booker.herokuapp.com/booking";
    String reqresBaseUrl= "https://reqres.in/api/users";
    String postManBaseUrl="https://api.postman.com";
    /**
     * Logging the request and response using log().all()
     */

    @Test
    public void testGetUseCaseOne(){
        given().
                baseUri(restfulBookerBaseUrl).
                header("Content-Type", "application/json").
                log().all().
                when().
                get("/1").
                then().
                log().all().
                assertThat().statusCode(200);
    }

    /**
     * Logging the request header using  log().headers().
     * Logging the response header using  log().body().
     */
    @Test
    public void testGetUseCaseTwo(){
        given().
                baseUri(restfulBookerBaseUrl).
                header("Content-Type", "application/json").
                log().headers().
                when().
                get("/1").
                then().
                log().body().
                assertThat().statusCode(200);
    }

    /**
     * Logging the response information only when there is an error using log().ifError().
     */

    @Test
    public void testGetUseCaseThree(){
        given().
                baseUri(postManBaseUrl).
                header("Content-Type", "application/json").
                log().headers().
                when().
                get("/workspaces").
                then().
                log().ifError().
                assertThat().statusCode(200);
    }

    /**
     * Logging the response information only when validation(statusCode) fails using ifValidationFails() .
     */

    @Test
    public void testGetUseCaseFour(){
        given().
                baseUri(reqresBaseUrl).
                header("Content-Type", "application/json").
                log().ifValidationFails().
                when().
                get("/23").
                then().
                log().ifValidationFails().
                assertThat().statusCode(200);
    }

    /**
     * Logging the response information only when validation(statusCode) fails using enableLoggingOfRequestAndResponseIfValidationFails().
     */

    @Test
    public void testGetUseCaseFive(){
        given().
                baseUri(reqresBaseUrl).
                header("Content-Type", "application/json").
                config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())).
                when().
                get("/23").
                then().
                assertThat().statusCode(200);
    }
}
