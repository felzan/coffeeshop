package com.felzan.coffeeshop.adapters.web.cart.requestbody;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.felzan.coffeeshop.application.dto.CartDTO;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartRequest {

  String sessionId;
  Long userId;
  Map<Long, Integer> cartItems;
  String status;

  public CartDTO toDTO() {
    return CartDTO.builder()
        .sessionId(getSessionId())
        .userId(getUserId())
        .cartItems(getCartItems())
        .status(getStatus())
        .build();
  }

}