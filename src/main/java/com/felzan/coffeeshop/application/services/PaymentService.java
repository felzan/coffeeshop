package com.felzan.coffeeshop.application.services;

import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.adapters.partners.adyen.AdyenClient;
import com.felzan.coffeeshop.adapters.partners.adyen.Amount;
import com.felzan.coffeeshop.adapters.partners.adyen.PaymentMethod;
import com.felzan.coffeeshop.adapters.partners.adyen.PaymentRequest;
import com.felzan.coffeeshop.adapters.partners.adyen.PaymentResponse;
import com.felzan.coffeeshop.application.models.PaymentInfo;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class PaymentService {

  AdyenClient client;

  public boolean execute(PaymentInfo paymentInfo) {
    var value = paymentInfo.getValue();
    var token = paymentInfo.getCardToken();
    var tokenCvc = paymentInfo.getCvc();
    var shopperReference = paymentInfo.getUserReference();
    var reference = UUID.randomUUID().toString();
    var returnUrl = "https://felzan.com";
    var merchantAccount = "FelzanECOM";
    var amount = Amount.builder()
        .currency("BRL")
        .value(value)
        .build();
    var paymentMethod = PaymentMethod.builder()
        .type("scheme")
        .tokenCvc(tokenCvc)
        .storedPaymentMethodId(token)
        .build();

    var paymentRequest = PaymentRequest.builder()
        .amount(amount)
        .reference(reference)
        .paymentMethod(paymentMethod)
        .shopperReference(shopperReference)
        .returnUrl(returnUrl)
        .merchantAccount(merchantAccount)
        .build();

    PaymentResponse response =
        client.payment(paymentRequest);
    return !response.getPspReference().isEmpty();
  }
}
