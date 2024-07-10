package service;

import java.util.List;
import java.util.UUID;

import entity.UserEntity;
import exception.UserNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;

//Dependency Injection - single bean instance
@ApplicationScoped
public class UserService {
    public UserEntity createUser(UserEntity userEntity) {
        UserEntity.persist(userEntity);
        return userEntity;
    }
    
    public List<UserEntity> findAll(Integer page, Integer pageSize) {
        return UserEntity.findAll().page(page, pageSize).list();
    }

    public UserEntity findById(UUID id) {
        return (UserEntity) UserEntity.findByIdOptional(id).orElseThrow(UserNotFoundException::new);
    }

    public UserEntity updateUser(UUID id, UserEntity userEntity) {
        var user = findById(id);
        
        //TODO: improve to update if value change 
        user.name = userEntity.name;
        user.document = userEntity.document;
        user.email = userEntity.email;
        user.phone = userEntity.phone;

        UserEntity.persist(user);

        return user;
    }

    public void deleteById(UUID id) {
        var user = findById(id);
        UserEntity.deleteById(user.id);
    }
}
