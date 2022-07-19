package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDates {

    @JsonProperty("checkin")
    private String checkin;

    @JsonProperty("checkout")
    private String checkout;

    @Override
    public String toString()
    {
        return "ClassPojo [checkin = "+checkin+", checkout = "+checkout+"]";
    }
}