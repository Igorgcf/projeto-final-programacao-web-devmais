package tech.ada.authuser.services.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.ada.authuser.dtos.UserDTODummyJson;
import tech.ada.authuser.dtos.UserResponseDTODummy;
import tech.ada.authuser.entities.UserDummyJson;
import tech.ada.authuser.repositories.DummyJsonClient;
import tech.ada.authuser.services.UserServiceDummyJson;
import tech.ada.authuser.services.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class UserServiceDummyJsonImpl implements UserServiceDummyJson {

    @Autowired
    private DummyJsonClient dummyJsonClient;

    @Cacheable(value = "users")
    @Transactional(readOnly = true)
    @Override
    public List<UserDTODummyJson> findAll() {

        UserResponseDTODummy user = dummyJsonClient.findAll();

        return user.getUsers();
    }

    @Cacheable(value = "users")
    @Transactional(readOnly = true)
    @Override
    public UserDTODummyJson findById(Long id) {

        Optional<UserDummyJson> obj = dummyJsonClient.findById(id);
        UserDummyJson entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id not found: " + id));
        return new UserDTODummyJson(entity);
    }

    @CachePut(value = "users")
    @Transactional
    @Override
    public UserDTODummyJson insert(UserDTODummyJson dto) {

        UserDummyJson entity = new UserDummyJson();
        copyDtoToEntity(entity, dto);
        dummyJsonClient.insert(entity);
        return new UserDTODummyJson(entity);
    }

    @CachePut(value = "users")
    @Transactional
    @Override
    public UserDTODummyJson update(Long id, UserDTODummyJson dto) {

        Optional<UserDummyJson> obj = dummyJsonClient.findById(id);
        UserDummyJson entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id not found: " + id));
        copyDtoToEntity(entity, dto);
        dummyJsonClient.update(id, entity);
        return new UserDTODummyJson(entity);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void deleteById(Long id) {

        Optional<UserDummyJson> obj = dummyJsonClient.findById(id);
        UserDummyJson entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id not found: " + id));
        dummyJsonClient.deleteById(id);
    }

    @Scheduled(fixedRate = 200000)
    @CacheEvict(value = "users", allEntries = true)
    public void clearCache(){
        log.info("Cache cleared");
    }

    public void copyDtoToEntity(UserDummyJson entity, UserDTODummyJson dto){
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAge(dto.getAge());
        entity.setEmail(dto.getEmail());
    }
}
