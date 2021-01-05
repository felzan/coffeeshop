package com.felzan.coffeeshop.application.services;

import com.felzan.coffeeshop.application.config.JWTTokenProvider;
import com.felzan.coffeeshop.application.dto.UserDTO;
import com.felzan.coffeeshop.application.models.User;
import com.felzan.coffeeshop.application.ports.in.user.UserIn;
import com.felzan.coffeeshop.application.ports.out.FindUser;
import com.felzan.coffeeshop.application.ports.out.SaveUser;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class UserService implements UserIn {

    SaveUser saveUser;
    FindUser findUser;
    PasswordEncoder passwordEncoder;
    JWTTokenProvider jwtTokenProvider;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String create(UserDTO userDTO) {
        // TODO: Can encode during serialization?
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        saveUser.save(userDTO.toUser());
        return jwtTokenProvider.generateToken(userDTO);
    }

    @Override
    public String login(UserDTO userDTO) {
        User userFound = findUser.findByUsername(userDTO.getUsername());
        if (!passwordEncoder.matches(userDTO.getPassword(), userFound.getPassword())) {
            //TODO: User password not match exception
            throw new RuntimeException("User password not match.");
        }
        return jwtTokenProvider.generateToken(userDTO);
    }

    public boolean exists(UserDTO userDTO) {
        User user = findUser.findByUsername(userDTO.getUsername());
        return user != null;
    }

}
