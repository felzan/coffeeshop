package com.felzan.coffeeshop.application.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import com.felzan.coffeeshop.application.config.JWTTokenProvider;
import com.felzan.coffeeshop.application.dto.UserDTO;
import com.felzan.coffeeshop.application.models.User;
import com.felzan.coffeeshop.application.ports.out.FindUser;
import com.felzan.coffeeshop.application.ports.out.SaveUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  private static final String token =
      "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmZWx6YW4iLCJpYXQiOjE2MDg1NjE1NzEsImV4cCI6MTYwODU5MDM3MX0.GPZbOmee2tZlytuFDf5v3duL2vEhmPwMAanDdIU-q7A";
  @InjectMocks
  private UserService userService;
  @Mock
  private SaveUser saveUser;
  @Mock
  private FindUser findUser;
  @Mock
  private PasswordEncoder passwordEncoder;
  @Mock
  private JWTTokenProvider jwtTokenProvider;

  public static UserDTO givenUser() {
    return UserDTO.builder()
        .username("user")
        .password("pass")
        .email("email@mail.com")
        .build();
  }

  public static User user() {
    return User.builder()
        .username("user")
        .password("{bcrypt}pass")
        .email("email@mail.com")
        .build();
  }

  @Test
  void create() {
    UserDTO userDTO = spy(givenUser());
    when(passwordEncoder.encode("pass"))
        .thenReturn("{bcrypt}pass");
    doNothing().when(saveUser).save(user());
    when(jwtTokenProvider.generateToken(userDTO))
        .thenReturn(token);

    userService.create(userDTO);
  }

  @Test
  @DisplayName("Should return token when user login.")
  void login() {
    UserDTO userDTO = spy(givenUser());
    User user = user();

    when(findUser.findByUsername(userDTO.getUsername()))
        .thenReturn(user);
    when(passwordEncoder.matches(userDTO.getPassword(), user.getPassword()))
        .thenReturn(true);
    when(jwtTokenProvider.generateToken(userDTO))
        .thenReturn(token);

    userService.login(userDTO);
  }

  @Test
  @DisplayName("Should throw exception when user not found by username.")
  void login_userNotFound() {
    UserDTO userDTO = spy(givenUser());

    when(findUser.findByUsername(userDTO.getUsername()))
        .thenThrow(RuntimeException.class);

    assertThrows(RuntimeException.class, () -> userService.login(userDTO));
  }

  @Test
  @DisplayName("Should throw exception when user password not match.")
  void login_userPasswordNotMatch() {
    UserDTO userDTO = spy(givenUser());
    User user = user();

    when(findUser.findByUsername(userDTO.getUsername()))
        .thenReturn(user);
    when(passwordEncoder.matches(userDTO.getPassword(), user.getPassword()))
        .thenReturn(false);

    assertThrows(RuntimeException.class, () -> userService.login(userDTO),
        "User password not match.");
  }

  @Test
  void exists() {
    UserDTO userDTO = givenUser();

    when(findUser.findByUsername("user"))
        .thenReturn(user());

    assertTrue(userService.exists(userDTO));
  }
}