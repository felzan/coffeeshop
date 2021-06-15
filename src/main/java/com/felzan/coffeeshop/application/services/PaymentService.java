package com.felzan.coffeeshop.application.services;

import static lombok.AccessLevel.PRIVATE;

import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class PaymentService {

  public boolean execute() {
    log.info("Call external payment service");
    return new Random().nextBoolean();
  }
}
