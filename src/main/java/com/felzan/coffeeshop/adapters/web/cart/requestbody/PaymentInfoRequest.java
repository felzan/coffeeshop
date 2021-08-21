package com.felzan.coffeeshop.adapters.web.cart.requestbody;

import static lombok.AccessLevel.PRIVATE;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.felzan.coffeeshop.application.dto.PaymentInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class PaymentInfoRequest {

  String cardToken;
  @JsonAlias("cvv")
  String cvc;

  public PaymentInfoDTO toDto() {
    return PaymentInfoDTO.builder()
        .cardToken(cardToken)
        .cvc(cvc)
        .build();
  }
}
