package test;

import baseUrl.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.HerokuAppBookingPojo;
import pojos.HerokuAppBookingdatesPojo;
import pojos.HerokuAppExpDataPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C21_Post_PojoCozum extends HerokuappBaseUrl {
    /*

      https://restful-booker.herokuapp.com/booking url’ine asagidaki body'ye sahip bir POST request
      gonderdigimizde donen response’un id haric asagidaki gibi oldugunu test edin.

       	Request body
       	{
       	"firstname" : "Ahmet",
       	"lastname" : “Bulut",
       	"totalprice" : 500,
       	"depositpaid" : false,
       	"bookingdates" :{
       	"checkin" : "2021-06-01",
       	"checkout": "2021-06-10"
       	 },
       	 "additionalneeds" : "wi-fi"
       	}


        Response Body{

        {
        "bookingid":24,
        "booking":{
        "firstname":"Ahmet",
        "lastname":"Bulut",
        "totalprice":500,
        "depositpaid":false,
        "bookingdates":{
        "checkin":"2021-06-01",
        "checkout":"2021-06-10"
        },
        "additionalneeds":"wi-fi"
        }
        }


     */
    @Test
    public void post01(){

        // 1- Url ve Body hazırla
        specHerokuapp.pathParams("pp1","booking");
        HerokuAppBookingdatesPojo bookingdates =
        new HerokuAppBookingdatesPojo("2021-06-01","2021-06-10");
        HerokuAppBookingPojo booking = new HerokuAppBookingPojo("Ahmet","Bulut",500,false,
                bookingdates,"wi-fi");

        System.out.println("reqBody = " + booking);
        // 2- Expected Data hazırla

        System.out.println("==================================================");
       HerokuAppExpDataPojo expData = new HerokuAppExpDataPojo(27,booking);
        // 3- Response'u kaydet
        Response response = given().
                            spec(specHerokuapp).
                            contentType(ContentType.JSON).
                            body(booking).
                            post("/{pp1}");
        response.prettyPrint();

        // 4- Assertion
        HerokuAppExpDataPojo resPojo = response.as(HerokuAppExpDataPojo.class);

        assertEquals(expData.getBooking().getFirstname(),resPojo.getBooking().getFirstname());
        assertEquals(expData.getBooking().getLastname(),resPojo.getBooking().getLastname());
        assertEquals(expData.getBooking().getTotalprice(),resPojo.getBooking().getTotalprice());
        assertEquals(expData.getBooking().isDepositpaid(),resPojo.getBooking().isDepositpaid());
        assertEquals(expData.getBooking().getAdditionalneeds(),resPojo.getBooking().getAdditionalneeds());
        assertEquals(expData.getBooking().getBookingdates().getCheckin(),
                resPojo.getBooking().getBookingdates().getCheckin());
        assertEquals(expData.getBooking().getBookingdates().getCheckout(),
                resPojo.getBooking().getBookingdates().getCheckout());

    }
}
