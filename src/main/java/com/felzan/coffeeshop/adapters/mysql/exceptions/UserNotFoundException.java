package com.felzan.coffeeshop.adapters.mysql.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(value = NOT_FOUND, reason = "User not found.")
public class UserNotFoundException extends RuntimeException {
}
