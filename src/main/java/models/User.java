package models;

import lombok.*;

@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String firstname;

    private String lastname;
}
