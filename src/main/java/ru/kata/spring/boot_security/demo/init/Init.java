package ru.kata.spring.boot_security.demo.init;

import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Set;

@Component
public class Init {

    private final UserService userService;
    private final RoleRepository roleRepository;

    public Init(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        Role role = new Role("ROLE_USER");
        roleRepository.save(role);

        Role role1 = new Role("ROLE_ADMIN");
        roleRepository.save(role1);

        User user = new User();
        user.setFirstName("user2");
        user.setLastName("user");
        user.setAge(20L);
        user.setPassword("user");
        user.setRoles(Set.copyOf(Collections.singletonList(role)));
        userService.add(user);

        User user1 = new User();
        user1.setFirstName("admin2");
        user1.setLastName("admin");
        user1.setAge(30L);
        user1.setPassword("admin");
        user1.setRoles(Set.copyOf(Collections.singletonList(role1)));
        userService.add(user1);
    }
}