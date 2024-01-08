package com.example.service;

import com.example.model.dto.request.UserRequestDTO;
import com.example.model.dto.response.UserResponseDTO;
import com.example.model.entity.User;

public interface UserService {
    User register(User user);
    UserResponseDTO login(UserRequestDTO userRequestDTO);
    User findByUsername(String username);
    User findById(Long id);
    void changeStatus(Long id);
    User saveOrUpdate(User user);

}
