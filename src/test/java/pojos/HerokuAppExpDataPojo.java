package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HerokuAppExpDataPojo {

    private int bookingId;
    private HerokuAppBookingPojo booking;
}
