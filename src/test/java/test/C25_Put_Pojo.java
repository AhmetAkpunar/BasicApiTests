package test;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.PojoJasonPlaceHolderExpData;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C25_Put_Pojo extends JsonPlaceHolderBaseUrl {

    /*
     https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki body’e sahip bir PUT request
     yolladigimizda donen response’in response body’sinin
     asagida verilen ile ayni oldugunu test ediniz

     Request Body

            {
            "title":"Ahmet",
            "body":"Merhaba",
            "userId":10,
            "id":70
            }

        Response Body

            {
            "title":"Ahmet",
            "body":"Merhaba",
            "userId":10,
            "id":70
            }
     */

    @Test
    public void put01(){

        // 1- Url ve Body hazırla

        specJsonPlace.pathParams("pp1","posts","pp2",70);

        PojoJasonPlaceHolderExpData reqBody = new PojoJasonPlaceHolderExpData("Ahmet","Merhaba",
                10,70);
        System.out.println("reqBody = " + reqBody);

        // 2- Expected Data(Response) Hazırla

        PojoJasonPlaceHolderExpData expData = new PojoJasonPlaceHolderExpData("Ahmet","Merhaba",
                10,70);
        System.out.println(" ====================================================== ");
        System.out.println("expData = " + expData);

        // 3- Response'u kaydet
        Response response = given().spec(specJsonPlace).contentType(ContentType.JSON).
                            when().body(reqBody).put("/{pp1}/{pp2}");

        System.out.println(" ====================================================== ");
        response.prettyPrint();

        // 4- Assertion
        PojoJasonPlaceHolderExpData resPojo = response.as(PojoJasonPlaceHolderExpData.class);

        assertEquals(expData.getTitle(),resPojo.getTitle());
        assertEquals(expData.getBody(),resPojo.getBody());
        assertEquals(expData.getUserId(),resPojo.getUserId());
        assertEquals(expData.getId(),resPojo.getId());

    }















}
