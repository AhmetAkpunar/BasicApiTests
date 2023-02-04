package test;

import baseUrl.DummyBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.DummyDataPojo;
import pojos.DummyExpDataPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C26_GetMethod_Pojo extends DummyBaseUrl {
    SoftAssert softAssert = new SoftAssert();

    /*
        http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request gonderdigimizde
        donen response’un asagidaki gibi oldugunu test edin.

      Response Body

        {
        "status":"success",
        "data":{
        "id":3,
        "employee_name":"AshtonCox",
        "employee_salary":86000,
        "employee_age":66,
        "profile_image":"",
        "message":"Successfully! Record has been fetched."
        }
        }
     */

    @Test
    public void get01(){

        // 1- Url hazırla
        specDummy.pathParams("pp1","employee","pp2",3);

        // 2- Expected Data hazırla
        DummyDataPojo data = new DummyDataPojo(3,"Ashton Cox",86000,66,"");
        DummyExpDataPojo expResponse =
        new DummyExpDataPojo("success",data,"Successfully! Record has been fetched.");
        System.out.println("expResponse = " + expResponse);

        // 3- Response'u kaydet
        Response response = given().spec(specDummy).when().get("/{pp1}/{pp2}");
        response.prettyPrint();

        // Assertion

        DummyExpDataPojo resPojo = response.as(DummyExpDataPojo.class);

        assertEquals(expResponse.getStatus(),resPojo.getStatus());
        assertEquals(expResponse.getMessage(),resPojo.getMessage());
        assertEquals(expResponse.getData().getId(),resPojo.getData().getId());
        assertEquals(expResponse.getData().getEmployee_name(),resPojo.getData().getEmployee_name());
        assertEquals(expResponse.getData().getEmployee_salary(),resPojo.getData().getEmployee_salary());
        assertEquals(expResponse.getData().getEmployee_age(),resPojo.getData().getEmployee_age());
        assertEquals(expResponse.getData().getProfile_image(),resPojo.getData().getProfile_image());


    }
}
