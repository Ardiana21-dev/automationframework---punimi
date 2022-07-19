package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder //for this not sure how it will be used in other methods and need to see in next steps
@AllArgsConstructor
@NoArgsConstructor
public class BookingIdList {
    private List<BookingId> bookingIds;
//    I HAD DESERIALIZATION ISSUES WHEN USING THIS CLASS AS A WRAPPER FOR THE LIST OF OBJECTS, NOT SURE STILL WHY IT DIDN;T WORK
//            WILL HAVE TO FIND OUT LATER - so for now is not being used!!
}
