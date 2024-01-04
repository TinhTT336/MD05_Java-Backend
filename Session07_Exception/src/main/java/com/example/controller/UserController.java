package com.example.controller;

import com.example.exception.CustomException;
import com.example.model.dto.request.UserRequest;
import com.example.model.dto.response.UserResponse;
import com.example.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<Page<UserResponse>> index(@RequestParam(name = "limit", defaultValue = "3") int limit,
                                                    @RequestParam(name = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<UserResponse> userResponsePage = userService.findAll(pageable);
        return new ResponseEntity<>(userResponsePage, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<?> save(@RequestBody @Valid UserRequest userRequest) throws CustomException {
        UserResponse userResponse = userService.saveOrUpdate(userRequest);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);

//        //cach 2
//        try {
//            UserResponse userResponse = userService.saveOrUpdate(userRequest);
//            return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Wrong!",HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) throws CustomException {
        UserResponse userResponse = userService.findById(id);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody @Valid UserRequest userRequest) throws CustomException {
        UserResponse userResponse = userService.findById(id);
        if (userResponse == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userRequest.setId(userResponse.getId());
        UserResponse user = userService.saveOrUpdate(userRequest);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable Long id) throws CustomException {
        userService.changeStatus(id);
        UserResponse userResponse = userService.findById(id);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
}
