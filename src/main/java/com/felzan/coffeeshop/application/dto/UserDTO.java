package com.felzan.coffeeshop.application.dto;


import com.felzan.coffeeshop.application.models.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import static lombok.AccessLevel.PRIVATE;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(level = PRIVATE)
public class UserDTO extends BaseDTO {

    String username;
    String password;
    String email;
    String name;
    String status;
    String token;

    public User toUser() {
        return User.builder()
                .username(getUsername())
                .password(getPassword())
                .name(getName())
                .email(getEmail())
                .status(getStatus())
                .token(getToken())
                .build();
    }
}
