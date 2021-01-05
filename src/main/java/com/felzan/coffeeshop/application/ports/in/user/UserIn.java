package com.felzan.coffeeshop.application.ports.in.user;

import com.felzan.coffeeshop.application.dto.UserDTO;

public interface UserIn {

    String create(UserDTO userDTO);

    String login(UserDTO userDTO);
}
