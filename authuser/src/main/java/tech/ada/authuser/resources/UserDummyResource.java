package tech.ada.authuser.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.authuser.dtos.UserDTODummyJson;
import tech.ada.authuser.services.UserServiceDummyJson;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UserDummyResource {

    @Autowired
    private UserServiceDummyJson service;

    @GetMapping
    public ResponseEntity<List<UserDTODummyJson>> findAll(){
        List<UserDTODummyJson> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTODummyJson> findById(@PathVariable Long id){
        UserDTODummyJson dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<UserDTODummyJson> insert(@RequestBody UserDTODummyJson dto){
        dto = service.insert(dto);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTODummyJson> update(@PathVariable Long id, @RequestBody UserDTODummyJson dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.ok().body("User deleted successfully!");
    }
}
