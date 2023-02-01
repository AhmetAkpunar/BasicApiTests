package test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class C14_Put_SoftAssertIleExpectedDataTesti {

    /*
        http://dummy.restapiexample.com/api/v1/update/21 url’ine asagidaki body’ye sahip bir
        PUT request gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.

        /*
         Request Body
            {"status":"success",
            "data":{"name":“Ahmet","salary":"1230","age":"44","id":40}
            }

               Response Body
               {"status":"success",
               "data":{"status":"success","data":{"name":“Ahmet","salary":"1230","age":"44","id":40}
                },
                "message":"Successfully!Recordhasbeenupdated."
                }
            */

    @Test
    public void put01(){
        /*
         Request Body
            {"status":"success",
            "data":{"name":“Ahmet","salary":"1230","age":"44","id":40}
            }
         */


        // 1- Url hazırla 3P ise Body hazırla

        String url ="https://dummy.restapiexample.com/api/v1/update/21";

        JSONObject innerReqBodyData = new JSONObject();
        innerReqBodyData.put("name","Ahmet");
        innerReqBodyData.put("salary",1230);
        innerReqBodyData.put("age",44);
        innerReqBodyData.put("id",40);

        JSONObject reqBody = new JSONObject();
        reqBody.put("status","success");
        reqBody.put("data",innerReqBodyData);

       // System.out.println("request Body: " +reqBody);


        // 2- Expected Data Hazırla

        /*
          Response Body
               {"status":"success",
               "data":{"status":"success","data":{"name":“Ahmet","salary":"1230","age":"44","id":40}
                },
                "message":"Successfully!Recordhasbeenupdated."
                }
         */

        JSONObject dataInner = new JSONObject();
        dataInner.put("status","success");
        dataInner.put("data",innerReqBodyData);

        JSONObject responseBody = new JSONObject();
        responseBody.put("status","success");
        responseBody.put("data",dataInner);

        responseBody.put("message","Successfully! Record has been updated.");


        System.out.println("=======================================");
        System.out.println("expected Response Body: " + responseBody);

        // 3- Response'ı kaydet

        Response response = given().
                                    when().
                            contentType(ContentType.JSON).
                                    body(reqBody.toString()).
                            put(url);

        System.out.println("Actual Response Body : ");
        response.prettyPrint();

        // 4- Assertion

        JsonPath responseJsonPath = response.jsonPath();
        assertEquals(responseBody.get("status"),responseJsonPath.getString("status"));
        assertEquals(responseBody.get("message"),responseJsonPath.getString("message"));

    }
}
