package com.felzan.coffeeshop.application.exceptions;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = UNAUTHORIZED, reason = "Username and/or password not match.")
public class PasswordNotMatchException extends RuntimeException {

}
