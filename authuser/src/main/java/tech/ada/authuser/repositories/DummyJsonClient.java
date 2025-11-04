package tech.ada.authuser.repositories;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import tech.ada.authuser.dtos.UserResponseDTODummy;
import tech.ada.authuser.entities.UserDummyJson;

import java.util.Optional;

@FeignClient(name = "dummyJsonClient", url = "https://dummyjson.com")
public interface DummyJsonClient {

    @GetMapping("/users")
    UserResponseDTODummy findAll();

    @GetMapping("/users/{id}")
    Optional<UserDummyJson> findById(@PathVariable Long id);

    @PostMapping("/users/add")
    UserDummyJson insert(@RequestBody UserDummyJson dto);

    @PutMapping("/users/{id}")
    UserDummyJson update(@PathVariable Long id, @RequestBody UserDummyJson dto);

    @DeleteMapping("/users/{id}")
    void deleteById(@PathVariable Long id);

}

