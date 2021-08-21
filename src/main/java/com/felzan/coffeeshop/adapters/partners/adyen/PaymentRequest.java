package com.felzan.coffeeshop.adapters.partners.adyen;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

  private Amount amount;
  private String reference;
  private PaymentMethod paymentMethod;
  private String shopperReference;
  private String returnUrl;
  private String merchantAccount;

}

