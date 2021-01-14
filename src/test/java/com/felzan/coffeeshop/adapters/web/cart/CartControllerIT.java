package com.felzan.coffeeshop.adapters.web.cart;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.felzan.coffeeshop.CoffeeShopApplication;
import com.felzan.coffeeshop.adapters.mysql.cart.CartEntity;
import com.felzan.coffeeshop.adapters.mysql.cart.CartRepository;
import com.felzan.coffeeshop.adapters.mysql.cartitem.CartItemRepository;
import com.felzan.coffeeshop.adapters.mysql.user.UserRepository;
import com.felzan.coffeeshop.adapters.web.cart.requestbody.CartRequest;
import com.felzan.coffeeshop.application.dto.ProductDTO;
import com.felzan.coffeeshop.application.dto.UserDTO;
import com.felzan.coffeeshop.application.services.CartService;
import com.felzan.coffeeshop.application.services.ProductService;
import com.felzan.coffeeshop.application.services.UserService;
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

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@ExtendWith({SpringExtension.class})
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = CoffeeShopApplication.class)
@ContextConfiguration
@AutoConfigureMockMvc
@EnableAutoConfiguration
@AutoConfigureTestDatabase
class CartControllerIT {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private MockMvc mvc;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductService productService;

    @Test
    @DisplayName(value = "An user with valid token should be able to create a new cart.")
    void create() throws Exception {
        productService.create(ProductDTO.builder().name("Coca Cola").build());
        String token = userService.create(UserDTO.builder().username("admin").password("admin").build());

        CartRequest cartRequest = new CartRequest();
        cartRequest.setSessionId("sessionId");
        cartRequest.setCartItems(Map.of(1L, 6));

        mvc.perform(post("/api/v1/carts/")
                .header("Authorization", token)
                .contentType(APPLICATION_JSON)
                .content(toJson(cartRequest)));

        List<CartEntity> cartsFound = cartRepository.findAll();
        assertEquals(1, cartsFound.size());
    }

    @Test
    @DisplayName(value = "An user with valid cart should be able to order.")
    void checkout() throws Exception {
        productService.create(ProductDTO.builder().name("Coca Cola").build());
        String token = userService.create(UserDTO.builder().username("admin").password("admin").build());

        CartRequest cartRequest = new CartRequest();
        cartRequest.setSessionId("sessionId");
        cartRequest.setStatus("CHECKOUT");
        cartRequest.setCartItems(Map.of(1L, 6));

        mvc.perform(post("/api/v1/carts/")
                .header("Authorization", token)
                .contentType(APPLICATION_JSON)
                .content(toJson(cartRequest)));

        List<CartEntity> cartsFound = cartRepository.findAll();
        assertEquals(1, cartsFound.size());
        assertNotNull(cartsFound.get(0).getStatus());
    }

    @Test
    @WithMockUser("admin")
    @DisplayName(value = "An user with valid token should be able to replace his cart.")
    void replace() throws Exception {
        productService.create(ProductDTO.builder().name("Coca Cola").build());
        String token = userService.create(UserDTO.builder().username("admin").password("admin").build());

        CartRequest cartRequest = new CartRequest();
        cartRequest.setSessionId("sessionId");
        cartRequest.setUserId(null);
        cartRequest.setCartItems(Map.of(1L, 6));

        cartService.save(cartRequest.toDTO());

        cartRequest.setCartItems(Map.of(1L, 12));

        mvc.perform(put("/api/v1/carts/1")
                .header("Authorization", token)
                .contentType(APPLICATION_JSON)
                .content(toJson(cartRequest)));

        List<CartEntity> cartsFound = cartRepository.findAll();
        assertEquals(1, cartsFound.size());
        assertEquals(Integer.valueOf(12), cartsFound.get(0).getCartItems().get(0).getQuantity());
        assertEquals("Coca Cola", cartsFound.get(0).getCartItems().get(0).getName());
    }

    // TODO: Test replace others users cart

    private static <T> String toJson(T object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}