package com.felzan.coffeeshop.adapters.web.admin;

import static com.felzan.coffeeshop.adapters.web.ConstantsController.ADMIN_USER;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.felzan.coffeeshop.application.models.User;
import com.felzan.coffeeshop.application.ports.in.user.FindUserIn;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RequestMapping(value = ADMIN_USER, produces = APPLICATION_JSON_VALUE)
public class AdminUserController {

  FindUserIn findUserIn;

  @GetMapping(value = "")
  public ResponseEntity<List<User>> post(@RequestHeader Long whiteLabelId) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(findUserIn.findAll(whiteLabelId));
  }

}
