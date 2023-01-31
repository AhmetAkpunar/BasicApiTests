package test;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C07_Get_BodyTekrarlardanKurtulma {

        /*
        https://restful-booker.herokuapp.com/booking/10 url’ine bir GET request gonderdigimizde donen Response’un,
        status code’unun 200,
        ve content type’inin application-json,
        ve response body’sindeki
        "firstname“in,"Susan",
        ve "lastname“in, "Jackson",
        ve "totalprice“in,612,
        ve "depositpaid“in,false,
        ve "additionalneeds“in,"Breakfast"
        oldugunu test edin

         */

    @Test
    public void get01(){

        // 1- Gerekli url hazırlanır. Get methodu olduğu için body'ye gerek yok
        String url = "https://restful-booker.herokuapp.com/booking/10";

        // 2- İsteniyorsa expected data hazırla

        // 3- Dönen response'ı kaydet

        Response response = given().when().get(url);

        response.prettyPrint();

        // 4- Expected Data ile Actual Data'nın karşılaştırılması - Assertion


        response.
                then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                header("Server","Cowboy").
                statusLine("HTTP/1.1 200 OK").
                body("firstname", equalTo("Mark"),
                "lastname",equalTo("Wilson"),
                        "totalprice",equalTo(203),
                        "depositpaid",equalTo(true),
                        "additionalneeds",equalTo(null));


    }

}
