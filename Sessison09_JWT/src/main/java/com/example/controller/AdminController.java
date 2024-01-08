package com.example.controller;

import com.example.model.entity.Role;
import com.example.model.entity.User;
import com.example.service.RoleService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Set;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("")
    public ResponseEntity<?> admin() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/change-status/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user.getRoles().stream().anyMatch(item -> item.getName().equals("ADMIN"))) {
            return new ResponseEntity<>("don't authorized!", HttpStatus.UNAUTHORIZED);
        }
        userService.changeStatus(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("/add-role/{id}")
    public ResponseEntity<?> addRole(@PathVariable Long id, @RequestParam("name") String[] roleNames) {
        User user = userService.findById(id);
        for (String roleName : roleNames) {
            user.getRoles().add(roleService.findByRoleName(roleName));
        }

        User updateUser = userService.saveOrUpdate(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @PatchMapping("/delete-role/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id, @RequestParam("name") String roleName) {
        User user = userService.findById(id);
        user.getRoles().removeIf(role -> role.getName().equals(roleName));

        User updateUser = userService.saveOrUpdate(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

}
