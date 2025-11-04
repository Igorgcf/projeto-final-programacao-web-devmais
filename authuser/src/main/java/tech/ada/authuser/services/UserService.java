package tech.ada.authuser.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tech.ada.authuser.dtos.UserDTO;

import java.util.UUID;

public interface UserService {

    Page<UserDTO> findAllPaged(Pageable pageable);

    Page<UserDTO> queryMethod(Pageable pageable, String name);

    UserDTO findById(UUID id);

    UserDTO insert (UserDTO dto);

    UserDTO update(UUID id, UserDTO dto);

    UserDTO updatePartial(UUID id, UserDTO dto);

    void deleteById(UUID id);
}
