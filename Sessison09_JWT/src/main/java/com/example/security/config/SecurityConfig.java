package com.example.security.config;

import com.example.security.jwt.AccessDenied;
import com.example.security.jwt.JWTEntryPoint;
import com.example.security.jwt.JWTProvider;
import com.example.security.jwt.JWTokenFilter;
import com.example.security.user_principle.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity //phan quyen trong url
@EnableMethodSecurity // them annotation nay thi co the phan quyen trong method (cac phuong thuc cua class)
public class SecurityConfig {
    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private JWTEntryPoint jwtEntryPoint;
    @Autowired
    private JWTokenFilter jwTokenFilter;
    @Autowired
    private AccessDenied accessDenied;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
      return  httpSecurity.csrf(AbstractHttpConfigurer::disable).authenticationProvider(authenticationProvider())
                .authorizeHttpRequests((auth)->
                        auth.requestMatchers("/auth/**","/categories","/products/**","/products").permitAll()
                                .requestMatchers("/user/**").hasAuthority("USER")
                                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                                .anyRequest().authenticated())
              .exceptionHandling(auth->
                      auth.authenticationEntryPoint(jwtEntryPoint)
                              .accessDeniedHandler(accessDenied))
              .addFilterBefore(jwTokenFilter, UsernamePasswordAuthenticationFilter.class)
              .build();

    }

    //cau hinh cach ma hoa mat khau
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

}
