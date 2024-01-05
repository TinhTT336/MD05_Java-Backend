package com.example.security;

import com.example.model.entity.Role;
import com.example.model.entity.User;
import com.example.repository.UserRepository;
import com.example.security.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        UserPrinciple userPrinciple = new UserPrinciple();
        userPrinciple.setUser(user);
        //cach 1:
        userPrinciple.setAuthorities(user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet()));

        //cach 2:
//        Collection<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
//
//        Set<Role> roles = user.getRoles();
//        for (Role role : roles) {
//            grantedAuthoritySet.add(new SimpleGrantedAuthority(role.getName()));
//        }

        return userPrinciple;
    }
}
