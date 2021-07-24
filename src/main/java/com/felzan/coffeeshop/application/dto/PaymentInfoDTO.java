package com.felzan.coffeeshop.application.dto;

import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.application.models.PaymentInfo;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class PaymentInfoDTO {

  BigDecimal value;
  String userReference;
  String cardToken;
  String cvc;

  public PaymentInfo toModel() {
    return PaymentInfo.builder()
        .value(value)
        .userReference(userReference)
        .cardToken(cardToken)
        .cvc(cvc)
        .build();
  }
}
