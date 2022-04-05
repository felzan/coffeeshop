package com.felzan.coffeeshop.adapters.web.admin;

import static com.felzan.coffeeshop.adapters.web.ConstantsController.ADMIN_CART;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.felzan.coffeeshop.application.models.Order;
import com.felzan.coffeeshop.application.ports.in.cart.CartIn;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RequestMapping(value = ADMIN_CART, produces = APPLICATION_JSON_VALUE)
public class AdminCartController {

  CartIn cartIn;

  @GetMapping(value = "")
  public ResponseEntity<List<Order>> findAll() {
    return ResponseEntity.ok(cartIn.findAll());
  }

  @GetMapping(value = "{cartId}")
  public ResponseEntity<Order> findOne(@PathVariable Long cartId) {
    return ResponseEntity.ok(cartIn.findOne(cartId));
  }

  @PatchMapping(value = "{cartId}")
  public ResponseEntity<?> updateStatus(@PathVariable Long cartId,
      @RequestParam String status) {
    cartIn.updateStatus(cartId, status);
    return ResponseEntity.ok().build();
  }

}
