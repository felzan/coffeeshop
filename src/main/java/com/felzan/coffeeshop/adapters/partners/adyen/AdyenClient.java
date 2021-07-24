package com.felzan.coffeeshop.adapters.partners.adyen;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "adyen", url = "https://checkout-test.adyen.com/checkout/v67")
public interface AdyenClient {

  @PostMapping("/payments")
  PaymentResponse payment(PaymentRequest paymentRequest);

}
