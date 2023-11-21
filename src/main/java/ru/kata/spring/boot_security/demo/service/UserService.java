package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService {

    void add(User user);

    public void updateUserById(Long id, User updateUser);

    void delete(Long id);

    List<User> getAllUser();

    User findByUsername(String name);

}