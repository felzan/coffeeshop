package com.felzan.coffeeshop.adapters.web.cart.requestbody;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.felzan.coffeeshop.application.dto.CartDTO;
import com.felzan.coffeeshop.application.dto.PaymentInfoDTO;
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
  String cardToken;
  @JsonAlias("cvv")
  String cvc;

  public CartDTO toDTO() {
    var paymentInfo = PaymentInfoDTO.builder()
        .cardToken(cardToken)
        .cvc(cvc)
        .build();
    return CartDTO.builder()
        .sessionId(getSessionId())
        .userId(getUserId())
        .cartItems(getCartItems())
        .status(getStatus())
        .paymentInfo(paymentInfo)
        .build();
  }
}