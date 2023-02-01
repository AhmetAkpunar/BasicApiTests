package test;

import baseUrl.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataDummy;

import static io.restassured.RestAssured.given;

public class C20_Get_TestDataKullanimi extends DummyBaseUrl {

     /*
    http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
    gonderdigimizde donen response’un status code’unun 200,
    content Type’inin application/json ve body’sinin asagidaki gibi oldugunu test edin.
    Expected Body
    {
    "status":"success",
    "data": {
            "id": 3,
            "employee_name":"Ashton Cox",
            "employee_salary":86000,
            "employee_age":66,
            "profile_image":""
            },
    "message":"Successfully! Record has been fetched."
    }
     */

    @Test
    public void get001(){

        // 1- Url hazırlama

        specDummy.pathParams("pp1","employee","pp2",3);

        // 2- Expected Data hazırla

        TestDataDummy testDataDummy = new TestDataDummy();

        JSONObject expData = testDataDummy.expectedBodyOlusturJson();

        // 3- Response'ı kaydet

        Response response = given().spec(specDummy).when().get("/{pp1}/{pp2}");

        // 4- Assertion

        JsonPath resJsonPath = response.jsonPath();

        Assert.assertEquals(expData.get("status"),resJsonPath.get("status"));
        Assert.assertEquals(expData.get("message"),resJsonPath.get("message"));
        Assert.assertEquals(expData.getJSONObject("data").get("id"),resJsonPath.get("data.id"));
        Assert.assertEquals(expData.getJSONObject("data").get("employee_name"),resJsonPath.get("data.employee_name"));
        Assert.assertEquals(expData.getJSONObject("data").get("employee_salary"),resJsonPath.get("data.employee_salary"));
        Assert.assertEquals(expData.getJSONObject("data").get("employee_age"),resJsonPath.get("data.employee_age"));
        Assert.assertEquals(expData.getJSONObject("data").get("profile_image"),resJsonPath.get("data.profile_image"));

    }
}
