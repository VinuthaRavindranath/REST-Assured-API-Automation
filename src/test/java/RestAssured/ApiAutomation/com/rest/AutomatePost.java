package RestAssured.ApiAutomation.com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class AutomatePost {

    String restfulBooker = "https://restful-booker.herokuapp.com/";


    @BeforeTest
    public void beforeTest() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(restfulBooker);
        builder.addHeader("Content-Type", "application/json");
        builder.log(LogDetail.ALL);
        RestAssured.requestSpecification = builder.build();

        ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
        resBuilder.expectStatusCode(200);
        resBuilder.log(LogDetail.ALL);
        RestAssured.responseSpecification = resBuilder.build();
    }

    /**
     * The below is in BDD style.
     * @return the bookingid
     * Using the post request I'm making a get request dynamically, without hardcoding the bookingid.
     */

    @Test
    public String validate_Post_Request_BDD_Style() {
        String payload = "{\n" +
                "    \"firstname\" : \"Vinu2\",\n" +
                "    \"lastname\" : \"test2\",\n" +
                "    \"totalprice\" : 2727,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2023-01-01\",\n" +
                "        \"checkout\" : \"2023-02-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        Response response = given().body(payload).
                when().post("booking/").
                then().extract().response();
        String bookingId=response.path("bookingid").toString();
        assertThat(response.path("booking.firstname").toString(), is(equalTo("Vinu2")));
        return bookingId;
    }

    @Test
    public void getBookingIdUseCaseOne(){
        given().
              when().get("booking/"+validate_Post_Request_BDD_Style()).
              then().extract().response();
    }

    /**
     * The below is in non- BDD style.
     * @return the bookingid
     * Using the post request I'm making a get request dynamically, without hardcoding the bookingid.
     */


    @Test
    public String validate_Post_Request_Non_BDD_Style() {
        String payload = "{\n" +
                "    \"firstname\" : \"Vinu3\",\n" +
                "    \"lastname\" : \"test3\",\n" +
                "    \"totalprice\" : 2728,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2023-01-01\",\n" +
                "        \"checkout\" : \"2023-02-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Lunch\"\n" +
                "}";

        Response response = with().body(payload).post("booking/");
        String bookingId=response.path("bookingid").toString();
        assertThat(response.path("booking.firstname").toString(), is(equalTo("Vinu3")));
        return bookingId;
    }

    @Test
    public void getBookingIdUseCaseTwo(){
        given().
                when().get("booking/"+validate_Post_Request_Non_BDD_Style()).
                then().extract().response();
    }
}
