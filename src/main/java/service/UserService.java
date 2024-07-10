package service;

import java.util.List;
import java.util.UUID;
import java.util.Set;

import entity.UserEntity;
import exception.UserNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

@ApplicationScoped
public class UserService {
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public UserEntity createUser(UserEntity user) {
        validateUser(user);
        user.persist();
        return user;
    }
    
    public List<UserEntity> findAll(Integer page, Integer pageSize) {
        return UserEntity.findAll().page(page, pageSize).list();
    }

    public UserEntity findById(UUID id) {
        return (UserEntity) UserEntity.findByIdOptional(id).orElseThrow(UserNotFoundException::new);
    }

    public UserEntity updateUser(UUID id, UserEntity userEntity) {
        var user = findById(id);
        validateUser(user);
        
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

     private void validateUser(UserEntity user) {
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
