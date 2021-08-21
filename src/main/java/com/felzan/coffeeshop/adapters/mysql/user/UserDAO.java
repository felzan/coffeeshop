package com.felzan.coffeeshop.adapters.mysql.user;

import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.adapters.mysql.exceptions.UserNotFoundException;
import com.felzan.coffeeshop.adapters.mysql.whitelabel.WhiteLabelEntity;
import com.felzan.coffeeshop.adapters.mysql.whitelabel.WhiteLabelRepository;
import com.felzan.coffeeshop.application.models.User;
import com.felzan.coffeeshop.application.ports.out.FindUser;
import com.felzan.coffeeshop.application.ports.out.SaveUser;
import com.felzan.coffeeshop.infrastructure.mapper.BeanMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class UserDAO implements SaveUser, FindUser {

  UserRepository userRepository;
  WhiteLabelRepository whiteLabelRepository;
  BeanMapper beanMapper;

  @Override
  public void save(User user) {
    UserEntity userEntity = beanMapper.userModelToEntity(user);
    WhiteLabelEntity whiteLabel =
        whiteLabelRepository.findById(user.getWhiteLabel().getId()).orElseThrow();
    userEntity.setWhiteLabel(whiteLabel);
    userRepository.save(userEntity);
  }

  @Override
  public User findByUsername(String username) {
    UserEntity userEntity = userRepository.findByUsername(username)
        .orElseThrow(UserNotFoundException::new);
    return beanMapper.userEntityToModel(userEntity);
  }

  @Override
  public User findById(Long userId) {
    UserEntity userEntity = userRepository.findById(userId)
        .orElseThrow(UserNotFoundException::new);
    return beanMapper.userEntityToModel(userEntity);
  }

  @Override
  public List<User> findAllByWhiteLabelId(Long id) {
    return userRepository.findAllByWhiteLabelId(id).orElseThrow().stream()
        .map(beanMapper::userEntityToModel)
        .collect(Collectors.toList());
  }

}
