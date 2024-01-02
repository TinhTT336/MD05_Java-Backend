package com.example.controller;

import com.example.model.entity.User;
import com.example.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

//    @GetMapping("/users")
//    public ResponseEntity<List<User>> getAllUsers() {
//        List<User> userList = userService.findAll();
//        return new ResponseEntity<>(userList, HttpStatus.OK);
//    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User>findById(@PathVariable Long id){
        User user=userService.findById(id);
        if(user==null){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<?> findByEmail(@RequestParam(value = "email",required = false) String email) {
        if(email!=null){
        User user = userService.findByEmail(email);
        System.out.println(user);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        }
        List<User>users=userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
