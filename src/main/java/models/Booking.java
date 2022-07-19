package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Booking {
    @JsonProperty("bookingid")
    private Integer bookingId;

    @JsonProperty("booking")
    private BookingDetailsDTO bookingDetailsDTO;
}