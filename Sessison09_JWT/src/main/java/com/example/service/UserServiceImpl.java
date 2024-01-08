package com.example.service;

import com.example.model.dto.request.UserRequestDTO;
import com.example.model.dto.response.UserResponseDTO;
import com.example.model.entity.Role;
import com.example.model.entity.User;
import com.example.repository.UserRepository;
import com.example.security.jwt.JWTProvider;
import com.example.security.user_principle.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private JWTProvider jwtProvider;
    @Autowired
    private RoleService roleService;
    @Override
    public User register(User user) {
        //ma hoa mat khau
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //role
        Set<Role>roles=new HashSet<>();

        //register cua user thi coi no la USER
        if(user.getRoles()==null||user.getRoles().isEmpty()){
            roles.add(roleService.findByRoleName("USER"));
        }else{
            //tao tai khoan va phan quyen thi phai co quyen ADMIN
            user.getRoles().forEach(role->{
                roles.add(roleService.findByRoleName(role.getName()));
            });
        }

        return userRepository.save(User.builder().
                id(user.getId()).status(user.isStatus()).
                username(user.getUsername()).fullName(user.getFullName()).
                password(user.getPassword()).roles(roles)
                .build());
    }

    @Override
    public UserResponseDTO login(UserRequestDTO userRequestDTO) {
        Authentication authentication;
        authentication=authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(userRequestDTO.getUsername(),userRequestDTO.getPassword()));
        UserPrinciple userPrinciple= (UserPrinciple) authentication.getPrincipal();

        return UserResponseDTO.builder().token(jwtProvider.generateToken(userPrinciple))
                .username(userPrinciple.getUsername())
                .roles(userPrinciple.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()))
                .build();

    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void changeStatus(Long id) {
        userRepository.changeStatus(id);
    }

    @Override
    public User saveOrUpdate(User user) {
        return userRepository.save(user);
    }


}
