package models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Data
@Builder //for this not sure how it will be used in other methods and need to see in next steps
@AllArgsConstructor
@NoArgsConstructor
public class BookingId {
    @JsonProperty("bookingid") //also to explain why it cases issues of mismatching without it when deserializing
    private Integer bookingId;
}
