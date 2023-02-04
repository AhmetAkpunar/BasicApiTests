package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.groovy.control.io.ReaderSource;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

public class C16_BaseUrlHerokuapp {

    /*
    Class icinde 2 Test metodu olusturun ve asagidaki testleri yapin
    1-  https://restful-booker.herokuapp.com/booking endpointine bir GET request gonderdigimizde
    donen response’un status code’unun 200 oldugunu ve Response’ta 12 booking oldugunu test edin

    2- https://restful-booker.herokuapp.com/booking endpointine asagidaki body’ye sahip bir POST
    request gonderdigimizde donen response’un status code’unun 200 oldugunu ve “firstname”
    degerinin “Ahmet” oldugunu test edin

        /*
        { "firstname" : "Ahmet",
        "lastname" : “Bulut",
        "totalprice" : 500,
        "depositpaid" : false,
        "bookingdates" : {
        "checkin" : "2021-06-01",
        "checkout" : "2021-06-10"},
        additionalneeds" : "wi-fi"}
     */


    @Test
    public void get01(){

        // 1- Url Hazırla

        String url ="https://restful-booker.herokuapp.com/booking";

        // 2- Expected Data hazırla
        // 3- Response'u kaydet
        Response response = given().when().get(url);
        //response.prettyPrint();

        // 4- Assertion

        response.then().
                assertThat().
                statusCode(200).
                body("bookingid", hasItem(3853));
    }
    @Test
    public void post01(){

        // 1- Url ve Body Hazırla
        String url ="https://restful-booker.herokuapp.com/booking";

        JSONObject innerBody = new JSONObject();
        innerBody.put("checkin","2021-06-01");
        innerBody.put("checkout","2021-06-10");

        JSONObject reqBody = new JSONObject();
        reqBody.put("firstname" , "Ahmet");
        reqBody.put("lastname" , "Bulut");
        reqBody.put("totalprice" , 500);
        reqBody.put("depositpaid" , false);
        reqBody.put("additionalneeds" , "wi-fi");
        reqBody.put("bookingdates",innerBody);

        System.out.println("reqBody = " + reqBody);
        // 2- Expected Data hazırla

        // 3- Response'u kaydet
        Response response = given().
                                    contentType(ContentType.JSON).
                           when().body(reqBody.toString()).post(url);
        response.prettyPrint();

        // 4- Assertion

        response.
                then().
                assertThat().
                statusCode(200).
                body("booking.bookingdates.checkin",Matchers.equalTo("2021-06-01"));
    }
}
