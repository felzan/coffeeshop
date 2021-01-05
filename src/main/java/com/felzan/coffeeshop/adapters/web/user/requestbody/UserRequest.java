package com.felzan.coffeeshop.adapters.web.user.requestbody;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.felzan.coffeeshop.application.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest {

    String username;
    String password;
    String name;
    String email;

    public UserDTO toUserDTO() {
        return UserDTO.builder()
                .username(getUsername())
                .password(getPassword())
                .name(getName())
                .email(getEmail())
                .build();
    }

}