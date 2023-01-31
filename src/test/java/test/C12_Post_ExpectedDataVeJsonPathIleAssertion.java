package test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.*;

public class C12_Post_ExpectedDataVeJsonPathIleAssertion {

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

                        Response Body
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

        // 1- Url ve Body hazırlanır

        String url ="https://restful-booker.herokuapp.com/booking";

        JSONObject innerJson = new JSONObject();
        innerJson.put("checkin","2021-06-01");
        innerJson.put("checkout","2021-06-10");

        JSONObject reqBody = new JSONObject();
        reqBody.put("firstname","Ahmet");
        reqBody.put("lastname","Ahmet");
        reqBody.put("totalprice", 500);
        reqBody.put("depositpaid", false);
        reqBody.put("bookingdates", innerJson);
        reqBody.put("additionalneeds","wi-fi");

        System.out.println("Request Body : "+reqBody);



        // 2- Expected Data hazırla

        JSONObject expectedBody = new JSONObject();

        expectedBody.put("bookingID",24);
        expectedBody.put("booking",reqBody);

        System.out.println("Expected Body : " +expectedBody);

        // 3- Response'ı kaydedelim

        Response response = given().
                                    contentType(ContentType.JSON).
                            when().
                                    body(reqBody.toString()).
                            post(url);


        //System.out.println("Response : ");
        //response.prettyPrint();

        // 4- Assertion

        JsonPath resJsonPath = response.jsonPath();
        assertEquals(expectedBody.getJSONObject("booking").get("firstname"),resJsonPath.get("booking" +
                                                                                                ".firstname"));
        assertEquals(expectedBody.getJSONObject("booking").get("lastname"),resJsonPath.get("booking" +
                                                                                               ".lastname"));
        assertEquals(expectedBody.getJSONObject("booking").get("additionalneeds"),resJsonPath.get(
                "booking.additionalneeds"));

        assertEquals(expectedBody.getJSONObject("booking").get("totalprice"),resJsonPath.get(
                "booking.totalprice"));
        assertEquals(expectedBody.getJSONObject("booking").get("depositpaid"),resJsonPath.get(
                "booking.depositpaid"));
        assertEquals(expectedBody.getJSONObject("booking").getJSONObject("bookingdates").get(
                "checkin"),
                resJsonPath.get("booking.bookingdates.checkin"));
        assertEquals(expectedBody.getJSONObject("booking").getJSONObject("bookingdates").get(
                "checkout"),
                resJsonPath.get("booking.bookingdates.checkout"));
    }

}
