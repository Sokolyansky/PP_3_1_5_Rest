package ru.kata.spring.boot_security.demo.configs.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<Unique, User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(Unique constraintAnnotation) {
    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {
        try {
            return !userRepository.existsByFirstName(user.getFirstName()) &&
                    (user.getId() == userRepository.findByUsername(user.getFirstName()).getId() || user.getId() == 0);
        }
        catch (Exception e){
            return true;
        }
    }
}