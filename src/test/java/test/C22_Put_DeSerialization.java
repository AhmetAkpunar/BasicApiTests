package test;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataJsonPlaceHolder;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class C22_Put_DeSerialization extends JsonPlaceHolderBaseUrl {

        /*
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
        Expected Data :
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

        TestDataJsonPlaceHolder testDataJsonPlaceHolder = new TestDataJsonPlaceHolder();

        Map<String, Object> reqBody = testDataJsonPlaceHolder.requestBodyOlusturMap();

        System.out.println(reqBody);

        // 2- Expected Data hazırla

        Map<String, Object> expDataMap = testDataJsonPlaceHolder.requestBodyOlusturMap();

        // 3- Response'u kaydet

        Response response = given().
                                    spec(specJsonPlace).
                            contentType(ContentType.JSON).
                                    when().
                            body(reqBody).
                                    put("/{pp1}/{pp2}");

        response.prettyPrint();

        // 4- Assertion

        Map<String,Object> respMap = response.as(Map.class);

        Assert.assertEquals(testDataJsonPlaceHolder.basariliStatusCode,response.getStatusCode());
        Assert.assertEquals(expDataMap.get("title"),respMap.get("title"));
        Assert.assertEquals(expDataMap.get("userID"),respMap.get("userID"));
        Assert.assertEquals(expDataMap.get("id"),respMap.get("id"));
        Assert.assertEquals(expDataMap.get("body"),respMap.get("body"));

    }


}
