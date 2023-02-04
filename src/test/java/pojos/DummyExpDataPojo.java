package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DummyExpDataPojo {

    private String status;
    private DummyDataPojo data;
    private String message;

    /*
        {
        "status":"success",
        "data":{
        "id":3,
        "employee_name":"AshtonCox",
        "employee_salary":86000,
        "employee_age":66,
        "profile_image":"",
        }
        "message":"Successfully! Record has been fetched."
        }
     */

}
