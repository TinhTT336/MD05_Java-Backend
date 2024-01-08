package com.example.controller;

import com.example.model.dto.request.UserRequestDTO;
import com.example.model.dto.response.UserResponseDTO;
import com.example.model.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody UserRequestDTO userRequestDTO){
        User user=userService.findByUsername(userRequestDTO.getUsername());
        if(!user.isStatus()){
            return new ResponseEntity<>("Your account has been blocked!",HttpStatus.OK);
        }
        UserResponseDTO userResponseDTO=userService.login(userRequestDTO);
        return new ResponseEntity<>(userResponseDTO,HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<?>register(@RequestBody User user){

        return new ResponseEntity<>(userService.register(user),HttpStatus.OK);
    }
}
