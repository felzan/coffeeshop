package com.felzan.coffeeshop.adapters.mysql.exceptions;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = NOT_FOUND, reason = "User not found.")
public class UserNotFoundException extends RuntimeException {

}
