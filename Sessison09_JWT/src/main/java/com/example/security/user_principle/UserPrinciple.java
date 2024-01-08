package com.example.security.user_principle;

import com.example.model.entity.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class UserPrinciple implements UserDetails {
    private User user;
    private Collection<? extends GrantedAuthority>authorities;


    //tao phuong thuc o class UserPrinciple thi khong can build o class UserDetailService
//    public static UserDetails build(User user){
//        return UserPrinciple.builder().user(user)
//                .authorities(user.getRoles().stream().map(item->
//                new SimpleGrantedAuthority(item.getName())).collect(Collectors.toSet())).build();
//    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
