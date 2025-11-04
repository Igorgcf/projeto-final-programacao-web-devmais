package tech.ada.authuser.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDummyJson {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
}
