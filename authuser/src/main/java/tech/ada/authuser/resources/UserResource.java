package tech.ada.authuser.resources;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.authuser.dtos.UserDTO;
import tech.ada.authuser.services.UserService;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
@OpenAPIDefinition(
        info = @Info(title = "User API", version = "v1"),
        security = @SecurityRequirement(name = "basicAuth")
)
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<Page<UserDTO>> findAllPaged(Pageable pageable){

        Page<UserDTO> page = service.findAllPaged(pageable);
        if(!page.isEmpty()){
            page.forEach(dto -> dto.add(linkTo(methodOn(UserResource.class).findById(dto.getId())).withSelfRel()));
        }
        return ResponseEntity.ok().body(page);
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<Page<UserDTO>> queryMethod(@PathVariable String name, Pageable pageable) {

        Page<UserDTO> page = service.queryMethod(pageable, name);
        if(!page.isEmpty()){
            page.forEach(dto -> dto.add(linkTo(methodOn(UserResource.class).findById(dto.getId())).withSelfRel()));
        }
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable UUID id){

        UserDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<UserDTO> insert(@RequestBody UserDTO dto){

        dto = service.insert(dto);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable UUID id, @RequestBody UserDTO dto){

        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> updatePartial(@PathVariable UUID id, @RequestBody UserDTO dto){

        dto = service.updatePartial(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id){

        service.deleteById(id);
        return ResponseEntity.ok().body("User deleted successfully!");
    }

}
