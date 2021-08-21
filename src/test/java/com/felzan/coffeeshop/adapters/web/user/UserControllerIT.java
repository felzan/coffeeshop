package com.felzan.coffeeshop.adapters.web.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.felzan.coffeeshop.CoffeeShopApplication;
import com.felzan.coffeeshop.adapters.mysql.user.UserEntity;
import com.felzan.coffeeshop.adapters.mysql.user.UserRepository;
import com.felzan.coffeeshop.adapters.mysql.whitelabel.WhiteLabelEntity;
import com.felzan.coffeeshop.adapters.mysql.whitelabel.WhiteLabelRepository;
import com.felzan.coffeeshop.adapters.web.ConstantsController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@ExtendWith({SpringExtension.class})
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = CoffeeShopApplication.class)
@ContextConfiguration
@AutoConfigureMockMvc
@EnableAutoConfiguration
@AutoConfigureTestDatabase
class UserControllerIT {

  @Autowired
  private MockMvc mvc;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private WhiteLabelRepository whiteLabelRepository;
//
//  @BeforeEach
//  void cleanUp() {
//    whiteLabelRepository.deleteAll();
//    userRepository.deleteAll();
//  }

  @Test
  @DisplayName(value = "Create user and return token.")
  void create() throws Exception {
    WhiteLabelEntity whiteLabelEntity = WhiteLabelEntity.builder()
        .name("WhiteLabel")
        .build();
    Long whiteLabelId = whiteLabelRepository.save(whiteLabelEntity).getId();

    var json = "{\n"
        + "    \"username\": \"felzan\",\n"
        + "    \"password\": \"senha\",\n"
        + "    \"whiteLabelId\": " + whiteLabelId + "\n"
        + "}";
    ResultActions resultActions = mvc.perform(post(ConstantsController.USER)
        .contentType(APPLICATION_JSON)
        .content(json));

    MockHttpServletResponse response = resultActions.andReturn().getResponse();

    UserEntity savedUser = userRepository.findByUsername("felzan").orElseThrow();

    assertNotNull(response.getContentAsString());
    assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    assertEquals("felzan", savedUser.getUsername());
    assertEquals("WhiteLabel", savedUser.getWhiteLabel().getName());
  }

  @Test
  @DisplayName(value = "Login user existing user and return token")
  void login() throws Exception {
    WhiteLabelEntity whiteLabelEntity = WhiteLabelEntity.builder()
        .name("WhiteLabel")
        .build();
    Long whiteLabelId = whiteLabelRepository.save(whiteLabelEntity).getId();

    var json = "{\n"
        + "    \"username\": \"userLogin\",\n"
        + "    \"password\": \"senha\",\n"
        + "    \"whiteLabelId\": " + whiteLabelId + "\n"
        + "}";
    mvc.perform(post(ConstantsController.USER)
        .contentType(APPLICATION_JSON)
        .content(json));
    json = "{\n"
        + "    \"username\": \"userLogin\",\n"
        + "    \"password\": \"senha\"\n"
        + "}";
    ResultActions resultActions = mvc.perform(patch(ConstantsController.USER)
        .contentType(APPLICATION_JSON)
        .content(json));
    MockHttpServletResponse response = resultActions.andReturn().getResponse();

    assertNotNull(response.getContentAsString());
    assertEquals(HttpStatus.ACCEPTED.value(), response.getStatus());
  }

  @Test
  @DisplayName(value = "Login with WRONG password")
  void loginWrongPassword() throws Exception {
    WhiteLabelEntity whiteLabelEntity = WhiteLabelEntity.builder()
        .name("WhiteLabel")
        .build();
    Long whiteLabelId = whiteLabelRepository.save(whiteLabelEntity).getId();

    var json = "{\n"
        + "    \"username\": \"wrongPass\",\n"
        + "    \"password\": \"senha\",\n"
        + "    \"whiteLabelId\": " + whiteLabelId + "\n"
        + "}";
    mvc.perform(post(ConstantsController.USER)
        .contentType(APPLICATION_JSON)
        .content(json));
    json = "{\n"
        + "    \"username\": \"wrongPass\",\n"
        + "    \"password\": \"wrong\"\n"
        + "}";
    ResultActions resultActions = mvc.perform(patch(ConstantsController.USER)
        .contentType(APPLICATION_JSON)
        .content(json));
    MockHttpServletResponse response = resultActions.andReturn().getResponse();

    assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatus());
    assertEquals("Username and/or password not match.", response.getErrorMessage());
  }
}