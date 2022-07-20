package models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingId {
    @JsonProperty("bookingid")
    private Integer bookingId;
}
