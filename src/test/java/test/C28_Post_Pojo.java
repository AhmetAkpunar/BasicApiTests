package test;

import baseUrl.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.POJOJsonPlaceHolder;
import pojos.PojoHerokuappBooking;
import pojos.PojoHerokuappBookingdates;
import pojos.PojoHerokuappExpectedBody;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C28_Post_Pojo extends HerokuappBaseUrl {

    /*
    https://restful-booker.herokuapp.com/booking url’ine
    asagidaki body'ye sahip bir POST request gonderdigimizde
    donen response’un id disinda asagidaki gibi oldugunu test edin.

                        Request body
                   {
                        "firstname" : "Ahmet",
                        "lastname" : “Bulut",
                        "totalprice" : 500,
                        "depositpaid" : false,
                        "bookingdates" : {
                                 "checkin" : "2021-06-01",
                                 "checkout" : "2021-06-10"
                                          },
                        "additionalneeds" : "wi-fi"
                    }

                        Response Body = Expected Data
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
                                        }
                        ,
                        "additionalneeds":"wi-fi"
                              }
                    }
         */


    @Test
    public void post01(){


        // 1- Url ve Body hazırla
        specHerokuapp.pathParams("pp1","booking");

        PojoHerokuappBookingdates bookingdates =
                new PojoHerokuappBookingdates("2021-06-01","2021-06-10");
        PojoHerokuappBooking reqBody = new PojoHerokuappBooking("Ahmet","Bulut",500,false,"wi-fi"
                ,bookingdates);

        // 2- Expected Data hazırla

        PojoHerokuappExpectedBody expBody = new PojoHerokuappExpectedBody(24,reqBody);
        System.out.println("expBody = " + expBody);

        // 3- Response'u kaydet

        Response response = given().spec(specHerokuapp).
                                    contentType(ContentType.JSON).
                                    when().
                                    body(reqBody).post("/{pp1}");

        response.prettyPrint();

        // 4- Assertion

        PojoHerokuappExpectedBody resPojo = response.as(PojoHerokuappExpectedBody.class);

        assertEquals(expBody.getBooking().getFirstname(),resPojo.getBooking().getFirstname());
        assertEquals(expBody.getBooking().getLastname(),resPojo.getBooking().getLastname());
        assertEquals(expBody.getBooking().getTotalprice(),resPojo.getBooking().getTotalprice());
        assertEquals(expBody.getBooking().isDepositpaid(),resPojo.getBooking().isDepositpaid());
        assertEquals(expBody.getBooking().getAdditionalneeds(),resPojo.getBooking().getAdditionalneeds());
        assertEquals(expBody.getBooking().getBookingdates().getCheckin(),
                resPojo.getBooking().getBookingdates().getCheckin());
        assertEquals(expBody.getBooking().getBookingdates().getCheckout(),
                resPojo.getBooking().getBookingdates().getCheckout());


    }
}
