package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DummyDataPojo {

    private int id;
    private String employee_name;
    private int employee_salary;
    private int employee_age;
    private String profile_image;


    /*
    {
        "id":3,
        "employee_name":"AshtonCox",
        "employee_salary":86000,
        "employee_age":66,
        "profile_image":"",
        }

     */
}
