package com.felzan.coffeeshop.adapters.mysql.user;

import com.felzan.coffeeshop.adapters.mysql.BaseEntity;
import com.felzan.coffeeshop.adapters.mysql.cart.CartEntity;
import com.felzan.coffeeshop.application.models.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Entity(name = "user")
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class UserEntity extends BaseEntity {

    String username;
    String password;
    @Email
    String email;
    @OneToMany(mappedBy = "user")
    List<CartEntity> carts;

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
