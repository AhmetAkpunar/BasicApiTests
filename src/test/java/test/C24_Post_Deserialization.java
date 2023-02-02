package test;

import baseUrl.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataDummy;
import testData.TestDataHerokuApp;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C24_Post_Deserialization extends HerokuappBaseUrl {

    /*
      https://restful-booker.herokuapp.com/booking url'ine asagidaki
      body'ye sahip bir POST request gonderdigimizde donen response'un
      id haric asagidaki gibi oldugunu test edin.
        Request body
   {
        "firstname" : "Ali",
        "lastname" : "Bak",
        "totalprice" : 500,
        "depositpaid" : false,
        "bookingdates" : {
                 "checkin" : "2021-06-01",
                 "checkout" : "2021-06-10"
                          },
        "additionalneeds" : "wi-fi"
    }
       Response Body
       {
       "bookingid":24,
       "booking":{
           "firstname":"Ali",
           "lastname":"Bak",
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

        TestDataHerokuApp testDataHerokuApp = new TestDataHerokuApp();
        HashMap<String,Object> reqBody = testDataHerokuApp.bookingOlusturMap();

        System.out.println(reqBody);
        // 2- Expected Data hazırla
        HashMap<String,Object> expData = testDataHerokuApp.expBodyOlusturMap();

        // 3- Respons'u kaydet
        Response response = given().
                                    spec(specHerokuapp).
                            contentType(ContentType.JSON).
                                    when().
                            body(reqBody).
                                    post("/{pp1}");

        response.prettyPrint();

        // 4- Assertion

        HashMap<String,Object> respMap = response.as(HashMap.class);
        assertEquals(((Map)expData.get("booking")).get("firstname"),
                     ((Map)respMap.get("booking")).get("firstname"));
        assertEquals(((Map)expData.get("booking")).get("lastname"),
                     ((Map)respMap.get("booking")).get("lastname"));
        assertEquals(((Map)expData.get("booking")).get("totalprice"),
                     ((Map)respMap.get("booking")).get("totalprice"));
        assertEquals(((Map)expData.get("booking")).get("depositpaid"),
                     ((Map)respMap.get("booking")).get("depositpaid"));
        assertEquals(((Map)expData.get("booking")).get("additionalneeds"),
                     ((Map)respMap.get("booking")).get("additionalneeds"));

        assertEquals( ((Map)(((Map)(expData.get("booking"))).get("bookingdates"))).get("checkin")
                ,     ((Map)(((Map)(respMap.get("booking"))).get("bookingdates"))).get("checkin"));
        assertEquals( ((Map)(((Map)(expData.get("booking"))).get("bookingdates"))).get("checkout")
                ,     ((Map)(((Map)(respMap.get("booking"))).get("bookingdates"))).get("checkout"));
    }
}
