package com.felzan.coffeeshop.adapters.web.whitelable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.felzan.coffeeshop.CoffeeShopApplication;
import com.felzan.coffeeshop.adapters.web.ConstantsController;
import com.felzan.coffeeshop.application.models.WhiteLabel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
class WhiteLabelControllerIT {

  @Autowired
  private MockMvc mvc;
  @Autowired
  private ObjectMapper objectMapper;

  @Test
  @DisplayName(value = "Create a white label resource.")
  void create() throws Exception {

    var json = "{\n"
        + "    \"name\": \"White label name\",\n"
        + "    \"description\": \"White label description\"\n"
        + "}";
    ResultActions resultActions = mvc.perform(post(ConstantsController.WHITE_LABEL)
        .contentType(APPLICATION_JSON)
        .content(json));

    var response = objectMapper
        .readValue(resultActions.andReturn().getResponse().getContentAsString(),
            WhiteLabel.class);

    assertNotNull(response.getId());
    assertNotNull(response.getCreatedAt());
    assertEquals("White label name", response.getName());
    assertEquals("White label description", response.getDescription());
  }

  @Test
  @DisplayName(value = "Create a white label resource. Then create their associations")
  void createWhiteLabelThenAssociate() throws Exception {
    var json = "{\n"
        + "    \"name\": \"White label name\",\n"
        + "    \"description\": \"White label description\"\n"
        + "}";
    ResultActions whiteLabelPostAction = mvc.perform(post(ConstantsController.WHITE_LABEL)
        .contentType(APPLICATION_JSON)
        .content(json));
    var whiteLabelSaved = objectMapper
        .readValue(whiteLabelPostAction.andReturn().getResponse().getContentAsString(),
            WhiteLabel.class);

    var userRequest = "{\n"
        + "    \"username\": \"felzan\",\n"
        + "    \"password\": \"senha\",\n"
        + "    \"whiteLabelId\": " + whiteLabelSaved.getId() + "\n"
        + "}";
    mvc.perform(post(ConstantsController.USER)
        .contentType(APPLICATION_JSON)
        .content(userRequest));

    ResultActions whiteLabelGetAction =
        mvc.perform(get(ConstantsController.WHITE_LABEL + "/" + whiteLabelSaved.getId())
            .content(json));
    var whiteLabelFound = objectMapper
        .readValue(whiteLabelGetAction.andReturn().getResponse().getContentAsString(),
            WhiteLabel.class);

    assertNotNull(whiteLabelFound.getId());
    assertNotNull(whiteLabelFound.getCreatedAt());
    assertEquals("felzan", whiteLabelFound.getUsers().get(0).getUsername());
    assertEquals("White label name", whiteLabelFound.getName());
    assertEquals("White label description", whiteLabelFound.getDescription());
  }
}