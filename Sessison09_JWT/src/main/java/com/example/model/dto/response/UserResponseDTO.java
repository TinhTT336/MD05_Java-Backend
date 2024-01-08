package com.example.model.dto.response;

import com.example.model.entity.Role;
import lombok.*;

import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class UserResponseDTO {
    private String token;
    private String username;
//    private String fullName;
//    private Boolean status;
    private Set<String> roles;
}
