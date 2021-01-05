package com.felzan.coffeeshop.adapters.mysql.user;

import com.felzan.coffeeshop.adapters.mysql.BaseEntity;
import com.felzan.coffeeshop.application.models.User;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Entity(name = "user")
@EqualsAndHashCode(callSuper = false)
public class UserEntity extends BaseEntity {

    private String username;
    private String password;
    @Email
    private String email;

    public UserEntity(User user) {
        setUsername(user.getUsername());
        setPassword(user.getPassword());
        setEmail(user.getEmail());
    }

    public User toUser() {
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .build();
    }
}
