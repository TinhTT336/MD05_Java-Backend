package com.example.service.user;

import com.example.exception.CustomException;
import com.example.model.dto.request.UserRequest;
import com.example.model.dto.response.UserResponse;
import com.example.model.entity.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public Page<UserResponse> findAll(Pageable pageable) {
        Page<User>userPage=userRepository.findAll(pageable);
        return userPage.map(UserResponse::new);
    }

    @Override
    public UserResponse saveOrUpdate(UserRequest userRequest) throws CustomException {
        if(userRequest.getId()!=null){
            //check trung
            UserResponse userResponse=findById(userRequest.getId());
            boolean usernameExist=userRepository.findAll().stream()
                    .anyMatch(user->!userRequest.getUsername().equals(userResponse.getUsername())&&userRequest.getUsername().equals(user.getUsername()));
            if(usernameExist){
                throw new CustomException("Username existed!!!!!!");
            }
            if(!userRepository.existsByPassword(userRequest.getPassword())){
                throw new CustomException("Password is not matched!");
            }

            User user=User.builder().id(userRequest.getId()).username(userRequest.getUsername())
                    .fullName(userRequest.getFullName())
                    .password(userRequest.getPassword())
                    .status(userRequest.isStatus()).build();
            User newUser=userRepository.save(user);
            return UserResponse.builder().id(newUser.getId())
                    .username(user.getUsername())
                    .fullName(user.getFullName())
                    .status(user.isStatus()).build();
        }
//them moi
        //check trung
        if(userRepository.existsByUsername(userRequest.getUsername())){
            throw new CustomException("Username existed!");
        }
        User user=User.builder().username(userRequest.getUsername())
                .fullName(userRequest.getFullName())
                .password(userRequest.getPassword())
                .status(userRequest.isStatus()).build();
        User newUser=userRepository.save(user);
         return UserResponse.builder().id(newUser.getId())
                 .username(user.getUsername())
                 .fullName(user.getFullName())
                 .status(user.isStatus()).build();
    }

    @Override
    public UserResponse findById(Long id) throws CustomException {
        Optional<User>userOptional =userRepository.findById(id);
        if(userOptional.isPresent()){
            User user=userOptional.get();
            return UserResponse.builder().id(user.getId())
                    .username(user.getUsername())
                    .fullName(user.getFullName())
                    .status(user.isStatus()).build();
        }

       throw new CustomException("Not Found");
    }

    @Override
    public void changeStatus(Long id) throws CustomException {
        UserResponse userResponse=findById(id);
        userRepository.changeStatus(id);
    }
}
