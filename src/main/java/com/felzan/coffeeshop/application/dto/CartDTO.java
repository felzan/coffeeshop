package com.felzan.coffeeshop.application.dto;


import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.application.models.Cart;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class CartDTO extends BaseDTO {

  String sessionId;
  Long userId;
  Map<Long, Integer> cartItems;
  String status;
  PaymentInfoDTO paymentInfo;

  public Cart toCart() {
    return Cart.builder()
        .id(getId())
        .sessionId(getSessionId())
        .userId(getUserId())
        .status(getStatus())
        .paymentInfo(paymentInfo.toModel())
        .build();
  }
}
