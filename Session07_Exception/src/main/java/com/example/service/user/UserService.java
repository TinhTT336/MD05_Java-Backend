package com.example.service.user;

import com.example.exception.CustomException;
import com.example.model.dto.request.UserRequest;
import com.example.model.dto.response.UserResponse;
import com.example.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserResponse> findAll(Pageable pageable);

    UserResponse saveOrUpdate(UserRequest useRequest) throws CustomException;

    UserResponse findById(Long id) throws CustomException;

    void changeStatus(Long id) throws CustomException;
}
