package test;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.POJOJsonPlaceHolder;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C27_Put_PojoClass extends JsonPlaceHolderBaseUrl {

            /*
            C27_Put_PojoClass
         https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki
         body’e sahip bir PUT request yolladigimizda donen response’in
         response body’sinin asagida verilen ile ayni oldugunu test ediniz
             Request Body

            {
            "title":"Ahmet",
            "body":"Merhaba",
            "userId":10,
            "id":70
            }
            Expected Body
            {
            "title":"Ahmet",
            "body":"Merhaba",
            "userId":10,
            "id":70
            }
             */

    @Test
    public void put01(){

        // 1- Url ve Body Hazırla

        specJsonPlace.pathParams("pp1","posts","pp2",70);

        POJOJsonPlaceHolder reqBody = new POJOJsonPlaceHolder("ahmet","Merhaba",70,10);

        System.out.println("reqBody : " + reqBody.toString());

        // 2- Expected Data Hazırla

        POJOJsonPlaceHolder expData = new POJOJsonPlaceHolder("ahmet","Merhaba",70,10);

        System.out.println("ExpData : " + expData);

        // 3- Response'u kaydet

        Response response = given().
                                    spec(specJsonPlace).
                           contentType(ContentType.JSON).
                                    when().
                           body(reqBody).
                                    put("/{pp1}/{pp2}");
        response.prettyPrint();

        // 4- Assertiom

        POJOJsonPlaceHolder resPojo = response.as(POJOJsonPlaceHolder.class);

        System.out.println("resPojo = " + resPojo);
        assertEquals(expData.getBody(),resPojo.getBody());
        assertEquals(expData.getId(),resPojo.getId());
        assertEquals(expData.getTitle(),resPojo.getTitle());
        assertEquals(expData.getUserId(),resPojo.getUserId());
    }
}
