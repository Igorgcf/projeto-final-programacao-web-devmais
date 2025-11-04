package tech.ada.authuser.services;

import tech.ada.authuser.dtos.UserDTODummyJson;

import java.util.List;

public interface UserServiceDummyJson {

    List<UserDTODummyJson> findAll();

    UserDTODummyJson findById(Long id);

    UserDTODummyJson insert(UserDTODummyJson dto);

    UserDTODummyJson update(Long id, UserDTODummyJson dto);

    void deleteById(Long id);
}
