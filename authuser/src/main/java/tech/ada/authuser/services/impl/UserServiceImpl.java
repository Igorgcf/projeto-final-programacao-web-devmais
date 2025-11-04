package tech.ada.authuser.services.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.ada.authuser.dtos.UserDTO;
import tech.ada.authuser.entities.User;
import tech.ada.authuser.repositories.UserRepository;
import tech.ada.authuser.services.UserService;
import tech.ada.authuser.services.exceptions.ResourceNotFoundException;

import java.util.Optional;
import java.util.UUID;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Cacheable(value = "users")
    @Transactional(readOnly = true)
    @Override
    public Page<UserDTO> findAllPaged(Pageable pageable) {

        Page<User> page = repository.findAll(pageable);
        return page.map(UserDTO::new);
    }

    @Cacheable(value = "users")
    @Transactional(readOnly = true)
    @Override
    public Page<UserDTO> queryMethod(Pageable pageable, String name) {

        Page<User> page = repository.findAllPagedByNameContainingIgnoreCase(pageable, name);
        return page.map(UserDTO::new);
    }

    @Cacheable(value = "users")
    @Transactional(readOnly = true)
    @Override
    public UserDTO findById(UUID id) {

        Optional<User> obj = repository.findById(id);
        User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id not found: " + id));

        return new UserDTO(entity);
    }

    @CachePut(value = "users")
    @Transactional
    @Override
    public UserDTO insert(UserDTO dto) {

        User entity = new User();
        copyDtoToEntity(entity, dto);
        repository.save(entity);
        return new UserDTO(entity);
    }

    @CachePut(value = "users")
    @Transactional
    @Override
    public UserDTO update(UUID id, UserDTO dto) {

        Optional<User> obj = repository.findById(id);
        User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id not found: " + id));
        copyDtoToEntity(entity, dto);
        repository.save(entity);
        return new UserDTO(entity);
    }

    @CachePut(value = "users")
    @Transactional
    @Override
    public UserDTO updatePartial(UUID id, UserDTO dto) {

        Optional<User> obj = repository.findById(id);
        User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id not found: " + id));
        entity.setName(dto.getName());
        entity.setLastName(dto.getLastName());
        repository.save(entity);
        return new UserDTO(entity);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void deleteById(UUID id) {

        Optional<User> obj = repository.findById(id);
        User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id not found: " + id));
        repository.deleteById(id);
    }

    @Scheduled(fixedRate = 200000)
    @CacheEvict(value = "users", allEntries = true)
    public void clearCache(){
        log.info("Cache cleared");
    }
    void copyDtoToEntity(User entity, UserDTO dto){

        entity.setName(dto.getName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setPassword(dto.getPassword());
    }

}
