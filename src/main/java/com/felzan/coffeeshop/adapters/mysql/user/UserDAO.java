package com.felzan.coffeeshop.adapters.mysql.user;

import com.felzan.coffeeshop.application.models.User;
import com.felzan.coffeeshop.application.ports.out.FindUser;
import com.felzan.coffeeshop.application.ports.out.SaveUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDAO implements SaveUser, FindUser {

    private final UserRepository userRepository;

    @Override
    public void save(User user) {
        UserEntity userEntity = new UserEntity(user);
        userRepository.save(userEntity);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                // TODO: User not found exception
                .orElseThrow(RuntimeException::new)
                .toUser();
    }

}
