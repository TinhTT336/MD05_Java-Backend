package com.example.service.user;

import com.example.model.entity.Category;
import com.example.model.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User save(User user);
    User update(User user);
    User findById(Long id);
    void delete(Long id);
    void register(User user);
    User findByEmail(String email);
}
