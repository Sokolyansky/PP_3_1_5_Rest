package ru.kata.spring.boot_security.demo.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;


import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public User findByUsername(String name) {
        return userRepository.findByUsername(name);
    }


    @Override
    @Transactional
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUserById(Long id, User updateUser) {
        updateUser.setId(id);
        if(!updateUser.getPassword().equals(userRepository.findById(id).get().getPassword())) {
            updateUser.setPassword(passwordEncoder.encode(updateUser.getPassword()));
        }
        userRepository.deleteById(id);
        userRepository.save(updateUser);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found"));
            userRepository.deleteById(id);

    }

    @Override
    public List<User> getAllUser() {

        return userRepository.findAll();
    }


}