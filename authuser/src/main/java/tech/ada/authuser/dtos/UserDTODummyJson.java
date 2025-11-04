package tech.ada.authuser.dtos;

import lombok.Data;
import tech.ada.authuser.entities.UserDummyJson;

@Data
public class UserDTODummyJson {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;

    public UserDTODummyJson() {
    }

    public UserDTODummyJson(Long id, String firstName, String lastName, Integer age, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }

    public UserDTODummyJson(UserDummyJson userDummyJson) {
        this.id = userDummyJson.getId();
        this.firstName = userDummyJson.getFirstName();
        this.lastName = userDummyJson.getLastName();
        this.age = userDummyJson.getAge();
        this.email = userDummyJson.getEmail();
    }
}
