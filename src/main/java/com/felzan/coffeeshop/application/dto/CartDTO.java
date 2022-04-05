package com.felzan.coffeeshop.application.dto;


import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.application.models.Order;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class CartDTO extends BaseDTO {

  String sessionId;
  Long userId;
  Map<Long, Integer> cartItems;
  String status;
  PaymentInfoDTO paymentInfo;

  public Order toCart() {
    return Order.builder()
        .id(getId())
        .sessionId(getSessionId())
        .userId(getUserId())
        .status(getStatus())
        .paymentInfo(paymentInfo.toModel())
        .build();
  }
}
