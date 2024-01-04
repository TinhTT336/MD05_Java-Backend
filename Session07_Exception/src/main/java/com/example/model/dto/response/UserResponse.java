package com.example.model.dto.response;

import com.example.model.entity.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class UserResponse {
    private Long id;
    private String username;
    private String fullName;
    private boolean status;

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.fullName = user.getFullName();
        this.status = user.isStatus();
    }
}
