package test;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C02_Get_Response_BilgileriAssertion {

        /*
        https://restful-booker.herokuapp.com/booking/10 url’ine bir GET request
        gonderdigimizde donen Response’un,
        status code’unun 200,
        ve content type’inin application/json; charset=utf-8,
        ve Server isimli Header’in degerinin Cowboy,
        ve status Line’in HTTP/1.1 200 OK
        oldugunu test edin.
         */

        @Test
        public void get01(){

            // 1- Gerekli url ve body hazırla
            String url = "https://restful-booker.herokuapp.com/booking/4139";

            // 2- İsteniyorsa expected data hazırla

            // 3- Dönen response'ı kaydet

            Response response = given().when().get(url);

            response.prettyPrint();

            // 4- Expected Data ile Actual Data'nın karşılaştırılması - Assertion

            response.
                    then().
                    assertThat().
                    statusCode(200).
                    contentType("application/json; charset=utf-8").
                    header("Server","Cowboy").
                    statusLine("HTTP/1.1 200 OK");
        }



}
