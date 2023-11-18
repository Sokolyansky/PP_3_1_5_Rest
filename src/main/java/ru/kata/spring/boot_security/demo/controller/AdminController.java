package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public AdminController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping()
    public String bootstrap(@ModelAttribute("user") User user, Principal principal, Model model) {
        User users = userService.findByUsername(principal.getName());
        model.addAttribute("user", users);
        model.addAttribute("users", userService.getAllUser());
        List<Role> roles = (List<Role>) roleRepository.findAll();
        model.addAttribute("allRoles", roles);
        return "Admin";
    }


    @PatchMapping("{id}/edit")
    public String edit(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.updateUserById(id, user);
        return "redirect:/admin/";
    }

    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/admin/";
    }

}