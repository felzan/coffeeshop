package com.felzan.coffeeshop.application.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@ResponseStatus(value = UNAUTHORIZED, reason = "Username and/or password not match.")
public class PasswordNotMatchException extends RuntimeException {

}
