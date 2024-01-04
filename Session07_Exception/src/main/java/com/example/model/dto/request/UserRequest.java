package com.example.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserRequest {
    private Long id;
    @NotEmpty(message = "Please fill username!")
    private String username;

    private String fullName;
    @NotEmpty(message = "Please fill password!")
    @Size(min = 3,message = "Password's length is greater than 3")
    private String password;
    private boolean status=true;
}
