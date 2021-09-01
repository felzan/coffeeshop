package com.felzan.coffeeshop.adapters.web.cart.requestbody;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.felzan.coffeeshop.application.dto.CartDTO;
import com.felzan.coffeeshop.application.dto.PaymentInfoDTO;
import java.util.Map;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartRequest {

  String sessionId;
  @NotNull Long userId;
  @NotNull Map<Long, Integer> cartItems;
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