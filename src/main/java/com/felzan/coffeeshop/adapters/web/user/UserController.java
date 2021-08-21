package com.felzan.coffeeshop.adapters.web.user;

import static com.felzan.coffeeshop.adapters.web.ConstantsController.USER;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.felzan.coffeeshop.adapters.web.user.requestbody.UserRequest;
import com.felzan.coffeeshop.application.ports.in.user.UserIn;
import com.felzan.coffeeshop.infrastructure.mapper.BeanMapper;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RequestMapping(value = USER, produces = APPLICATION_JSON_VALUE)
public class UserController {

  UserIn userIn;
  BeanMapper beanMapper;

  @PostMapping(value = "", consumes = APPLICATION_JSON_VALUE)
  public ResponseEntity<String> post(@RequestBody UserRequest userRequest) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(userIn.create(beanMapper.userRequestToDTO(userRequest)));
  }

  @PatchMapping(value = "", consumes = APPLICATION_JSON_VALUE)
  public ResponseEntity<String> login(@RequestBody UserRequest userRequest) {
    return ResponseEntity.status(HttpStatus.ACCEPTED)
        .body(userIn.login(beanMapper.userRequestToDTO(userRequest)));
  }

}
