package com.felzan.coffeeshop.application.services;

import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.application.config.JWTTokenProvider;
import com.felzan.coffeeshop.application.dto.UserDTO;
import com.felzan.coffeeshop.application.exceptions.PasswordNotMatchException;
import com.felzan.coffeeshop.application.models.User;
import com.felzan.coffeeshop.application.models.WhiteLabel;
import com.felzan.coffeeshop.application.ports.in.user.FindUserIn;
import com.felzan.coffeeshop.application.ports.in.user.UserIn;
import com.felzan.coffeeshop.application.ports.out.FindUser;
import com.felzan.coffeeshop.application.ports.out.SaveUser;
import com.felzan.coffeeshop.infrastructure.mapper.BeanMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class UserService implements UserIn, FindUserIn {

  SaveUser saveUser;
  FindUser findUser;
  PasswordEncoder passwordEncoder;
  JWTTokenProvider jwtTokenProvider;
  BeanMapper beanMapper;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public String create(UserDTO dto) {
    // TODO: Can encode during serialization?
    dto.setPassword(passwordEncoder.encode(dto.getPassword()));
    User userModel = beanMapper.userDTOToModel(dto);
    userModel.setWhiteLabel(WhiteLabel.builder().id(dto.getWhiteLabelId()).build());
    saveUser.save(userModel);
    return jwtTokenProvider.generateToken(dto);
  }

  @Override
  public String login(UserDTO userDTO) {
    User userFound = findUser.findByUsername(userDTO.getUsername());
    if (!passwordEncoder.matches(userDTO.getPassword(), userFound.getPassword())) {
      throw new PasswordNotMatchException();
    }
    return jwtTokenProvider.generateToken(userDTO);
  }

  @Override
  public List<User> findAll(Long whiteLabelId) {
    return findUser.findAllByWhiteLabelId(whiteLabelId);
  }

  public boolean exists(UserDTO userDTO) {
    User user = findUser.findByUsername(userDTO.getUsername());
    return user != null;
  }

}
