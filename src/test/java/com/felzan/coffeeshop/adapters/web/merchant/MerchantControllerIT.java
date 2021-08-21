package com.felzan.coffeeshop.adapters.web.merchant;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.felzan.coffeeshop.CoffeeShopApplication;
import com.felzan.coffeeshop.adapters.web.ConstantsController;
import com.felzan.coffeeshop.application.dto.MerchantDTO;
import com.felzan.coffeeshop.application.models.Merchant;
import com.felzan.coffeeshop.application.services.MerchantService;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
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
class MerchantControllerIT {

  @Autowired
  private MockMvc mvc;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private MerchantService merchantService;

  @AfterEach
  void clearDb() {
  }

  @Test
  @DisplayName(value = "Create a merchant with single working hour and single shift.")
  void create() throws Exception {

    var json = "{\n"
        + "    \"name\": \"Merchant name\",\n"
        + "    \"description\": \"Merchant description\",\n"
        + "    \"cnpj\": \"00011122333344\",\n"
        + "    \"workingHours\": [\n"
        + "        {\n"
        + "            \"days\": [\n"
        + "                \"MONDAY\",\n"
        + "                \"TUESDAY\",\n"
        + "                \"WEDNESDAY\",\n"
        + "                \"THURSDAY\",\n"
        + "                \"FRIDAY\"\n"
        + "            ],\n"
        + "            \"description\": \"Week days\",\n"
        + "            \"shifts\": [\n"
        + "                {\n"
        + "                    \"begin\": \"08:00:00\",\n"
        + "                    \"end\": \"23:00:00\"\n"
        + "                }\n"
        + "            ]\n"
        + "        }\n"
        + "    ]\n"
        + "}";
    ResultActions resultActions = mvc.perform(post(ConstantsController.MERCHANT)
        .contentType(APPLICATION_JSON)
        .content(json));

    var merchant = objectMapper
        .readValue(resultActions.andReturn().getResponse().getContentAsString(),
            Merchant.class);

    Set<DayOfWeek> expectedDays = Set.of(FRIDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY);

    assertNotNull(merchant.getId());
    assertNotNull(merchant.getCreatedAt());
    assertEquals("Merchant name", merchant.getName());
    assertEquals("Merchant description", merchant.getDescription());
    assertEquals("00011122333344", merchant.getCnpj());
    assertEquals(expectedDays, merchant.getWorkingHours().get(0).getDays());
    assertEquals("Week days", merchant.getWorkingHours().get(0).getDescription());
    assertEquals(LocalTime.parse("08:00:00"),
        merchant.getWorkingHours().get(0).getShifts().get(0).getBegin());
    assertEquals(LocalTime.parse("23:00:00"),
        merchant.getWorkingHours().get(0).getShifts().get(0).getEnd());
  }

  @Test
  @DisplayName("")
  void findById() throws Exception {
    var newMerchant = MerchantDTO.builder()
        .name("Merchant 1")
        //TODO: add more fields
        .build();
    merchantService.save(newMerchant);

    ResultActions resultActions = mvc.perform(get(ConstantsController.MERCHANT.concat("/1")));
    var merchant = objectMapper
        .readValue(resultActions.andReturn().getResponse().getContentAsString(),
            Merchant.class);
    assertEquals(Long.valueOf(1), merchant.getId());
    assertEquals("Merchant 1", merchant.getName());
  }
}