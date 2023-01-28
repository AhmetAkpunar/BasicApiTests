package test;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C01_Get_Api_Sorgulama {

            /*
            https://restful-booker.herokuapp.com/booking/3567 url’ine
            bir GET request gonderdigimizde donen Response’un,
            status code’unun 200,
            ve content type’inin application/json; charset=utf-8,
            ve Server isimli Header’in degerinin Cowboy,
            ve status Line’in HTTP/1.1 200 OK
            ve response suresinin 5 sn’den kisa oldugunu manuel olarak test ediniz.
            */

            // 1- Göndreceğimiz request için gerekli olan url'i ve ihtiyacımız varsa body'yi hazırla
            // 2- Eğer soruda bize verilmişse Expected Data hazırla
            // 3- Bize dönen response'ı Actual Data olarak kaydedeceğiz
            // 4- Expected Data ile Actual Data'nın karşılaştırılması - Assertion
    @Test
    public void get01(){

        // 1- Göndreceğimiz request için gerekli olan url'i ve ihtiyacımız varsa body'yi hazırla

        String url = "https://restful-booker.herokuapp.com/booking/4139";

        // 2- Eğer soruda bize verilmişse Expected Data hazırla

        // 3- Bize dönen response'ı Actual Data olarak kaydedeceğiz

        Response response = given().when().get(url);

        response.prettyPrint();

        int statusCode = response.getStatusCode();

        System.out.println("=============================================");
        System.out.println("Status Code : " + statusCode);
        System.out.println("=============================================");
        System.out.println("Content Type : " + response.getContentType());
        System.out.println("=============================================");
        System.out.println("Server Header Value : " + response.getHeader("Server"));
        System.out.println("=============================================");
        System.out.println("Status Line : " + response.getStatusLine());
        System.out.println("=============================================");
        System.out.println("Response Süresi : " + response.getTime());

        // 4- Expected Data ile Actual Data'nın karşılaştırılması - Assertion


    }
}
