package com.felzan.coffeeshop.adapters.web.cart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.felzan.coffeeshop.CoffeeShopApplication;
import com.felzan.coffeeshop.adapters.mysql.order.OrderEntity;
import com.felzan.coffeeshop.adapters.mysql.order.OrderRepository;
import com.felzan.coffeeshop.adapters.web.cart.requestbody.CartRequest;
import com.felzan.coffeeshop.application.dto.ProductDTO;
import com.felzan.coffeeshop.application.dto.UserDTO;
import com.felzan.coffeeshop.application.models.Order;
import com.felzan.coffeeshop.application.models.Product;
import com.felzan.coffeeshop.application.services.CartService;
import com.felzan.coffeeshop.application.services.ProductService;
import com.felzan.coffeeshop.application.services.UserService;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
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
class OrderControllerIT {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  @Autowired
  private MockMvc mvc;
  @Autowired
  private OrderRepository orderRepository;
  @Autowired
  private CartService cartService;
  @Autowired
  private UserService userService;
  @Autowired
  private ProductService productService;

  private static <T> String toJson(T object) {
    try {
      return OBJECT_MAPPER.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @AfterEach
  void clearDb() {
    productService.find().stream()
        .mapToLong(Product::getId)
        .forEach(id -> productService.delete(id));
    orderRepository.findAll().stream()
        .mapToLong(OrderEntity::getId)
        .forEach(id -> orderRepository.deleteById(id));
  }

  @Test
  @DisplayName(value = "An user with valid token should be able to create a new cart.")
  void create() throws Exception {
    Long productId = productService.create(ProductDTO.builder().name("Coca Cola").build()).getId();
    String token =
        userService.create(UserDTO.builder().username("felzan").password("password").build());

    CartRequest cartRequest = new CartRequest();
    cartRequest.setSessionId("sessionId");
    cartRequest.setCartItems(Map.of(productId, 6));

    ResultActions resultActions = mvc.perform(post("/api/v1/carts/")
        .header("Authorization", token)
        .contentType(APPLICATION_JSON)
        .content(toJson(cartRequest)));

    Long cartId = OBJECT_MAPPER
        .readValue(resultActions.andReturn().getResponse().getContentAsString(), Long.class);

    Order order = cartService.findOne(cartId);
    assertEquals(Integer.valueOf(6), order.getItemList().get(0).getQuantity());
    assertEquals("Coca Cola", order.getItemList().get(0).getName());
  }

  @Test
  @DisplayName(value = "An user with valid cart should be able to order.")
  void checkout() throws Exception {
    Product cocaCola = productService.create(ProductDTO.builder().name("Coca Cola").build());
    String token =
        userService.login(UserDTO.builder().username("felzan").password("password").build());

    CartRequest cartRequest = new CartRequest();
    cartRequest.setSessionId("sessionId");
    cartRequest.setStatus("CHECKOUT");
    cartRequest.setCartItems(Map.of(cocaCola.getId(), 6));

    ResultActions resultActions = mvc.perform(post("/api/v1/carts/")
        .header("Authorization", token)
        .contentType(APPLICATION_JSON)
        .content(toJson(cartRequest)));

    Long cartId = OBJECT_MAPPER
        .readValue(resultActions.andReturn().getResponse().getContentAsString(), Long.class);

    Order order = cartService.findOne(cartId);
    assertNotNull(order.getStatus());
  }

  // TODO: Test replace others users cart

  @Test
  @WithMockUser("felzan")
  @DisplayName(value = "An user with valid token should be able to replace his cart.")
  void replace() throws Exception {
    Product cocaCola = productService.create(ProductDTO.builder().name("Coca Cola").build());
    String token =
        userService.login(UserDTO.builder().username("felzan").password("password").build());

    CartRequest cartRequest = new CartRequest();
    cartRequest.setSessionId("sessionId");
    cartRequest.setUserId(null);
    cartRequest.setCartItems(Map.of(cocaCola.getId(), 6));

    Order order = cartService.save(cartRequest.toDTO());

    cartRequest.setCartItems(Map.of(cocaCola.getId(), 12));

    mvc.perform(put("/api/v1/carts/" + order.getId().toString())
        .header("Authorization", token)
        .contentType(APPLICATION_JSON)
        .content(toJson(cartRequest)));

    Optional<OrderEntity> cartEntity = orderRepository.findById(order.getId());
    assertTrue(cartEntity.isPresent());
    assertEquals(1, cartEntity.get().getOrderItems().size());
    assertEquals(Integer.valueOf(12), cartEntity.get().getOrderItems().get(0).getQuantity());
    assertEquals("Coca Cola", cartEntity.get().getOrderItems().get(0).getName());
  }
}