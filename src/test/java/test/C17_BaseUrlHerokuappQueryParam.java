package test;

import baseUrl.HerokuappBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C17_BaseUrlHerokuappQueryParam extends HerokuappBaseUrl {

    // Class icinde 3 Test metodu olusturun ve asagidaki testleri yapin


    @Test
    public void get01(){
         /*
        1-  https://restful-booker.herokuapp.com/booking endpointine bir GET
        request gonderdigimizde donen response’un status code’unun 200 oldugunu
        ve Response’ta 33071 id'ye sahip bir booking oldugunu test edin
        */

        // 1- Url Hazırlanır

        specHerokuapp.pathParam("pp1","booking");
        // 2- Expected Data hazırla
        // 3- Response'ı kaydet

        Response response = given().
                                    spec(specHerokuapp).
                            when().
                                    get("/{pp1}");

        // 4- Assertion

        response.then().assertThat().statusCode(200).body("bookingid", hasItem(2789));

    }
    @Test
    public void get02(){

        /*
        2- https://restful-booker.herokuapp.com/booking endpointine gerekli
        Query parametrelerini yazarak “firstname” degeri “Eric” olan rezervasyon
        oldugunu test edecek bir GET request gonderdigimizde, donen response’un
        status code’unun 200 oldugunu ve “Eric” ismine sahip en az bir booking oldugunu test edin
        */

        // 1- Url Hazırlanır

        specHerokuapp.pathParams("pp1","booking").queryParam("firstname","Eric");
        // 2- Expected Data hazırla
        // 3- Response'ı kaydet

        Response response = given().spec(specHerokuapp).when().get("/{pp1}");

        response.prettyPrint();
        // Assertion
        //JsonPath resJsonPath = response.jsonPath();
        response.then().assertThat().statusCode(200).body("bookingid",
                hasSize(1));

    }

    @Test
    public void get03(){

        /*
        3- https://restful-booker.herokuapp.com/booking endpointine gerekli Query
         parametrelerini yazarak “firstname” degeri “Jim” ve “lastname” degeri
         “Jackson” olan rezervasyon oldugunu test edecek bir GET request gonderdigimizde,
         donen response’un status code’unun 200 oldugunu ve “Jim Jackson” ismine sahip
         en az bir booking oldugunu test edin.
        */

        // 1- Url Hazırlanır

        specHerokuapp.pathParams("pp1","booking").
                      queryParams("firstname","Jim","lastname","Jackson");

        // 2- Expected Data hazırla
        // 3- Response'ı kaydet

        Response response = given().spec(specHerokuapp).when().get("/{pp1}");

        response.prettyPrint();

        response.then().assertThat().statusCode(200).body("bookingid",hasSize(1));

    }

}
