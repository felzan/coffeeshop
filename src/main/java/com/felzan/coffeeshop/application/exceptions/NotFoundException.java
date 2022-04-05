package com.felzan.coffeeshop.application.exceptions;

import static org.springframework.http.HttpStatus.PRECONDITION_REQUIRED;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = PRECONDITION_REQUIRED, reason = "Some or all items were not found.")
public class NotFoundException extends RuntimeException {

}
