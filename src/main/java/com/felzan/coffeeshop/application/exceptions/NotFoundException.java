package com.felzan.coffeeshop.application.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.PRECONDITION_REQUIRED;

@ResponseStatus(value = PRECONDITION_REQUIRED, reason = "Some or all items were not found.")
public class NotFoundException extends RuntimeException {

}
