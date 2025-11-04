package tech.ada.authuser.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTODummy {

    private List<UserDTODummyJson> users;
    private int total;
    private int skip;
    private int limit;
}
