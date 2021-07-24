package com.felzan.coffeeshop.application.services;

import com.felzan.coffeeshop.application.models.PaymentInfo;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class PaymentServiceTest {

  @Autowired
  private PaymentService paymentService;

  @Test
  void execute() {
    var paymentInfo = PaymentInfo.builder()
        .cardToken("8416240608387266")
        .cvc("737")
        .value(new BigDecimal(150))
        .userReference("felzan")
        .build();
    paymentService.execute(paymentInfo);
  }
}