package com.felzan.coffeeshop.application.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

@ResponseStatus(value = NOT_ACCEPTABLE, reason = "Cart stage does not allow updates.")
public class CannotUpdateCartException extends RuntimeException {

}
