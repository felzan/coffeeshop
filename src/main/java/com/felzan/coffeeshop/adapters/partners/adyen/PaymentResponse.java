package com.felzan.coffeeshop.adapters.partners.adyen;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {

  private String merchantReference;
  private String pspReference;
  private String resultCode;
  private Amount amount;

  private String refusalReason;
  private String refusalReasonCode;
}
