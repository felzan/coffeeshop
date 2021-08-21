package com.felzan.coffeeshop.adapters.web.cart;

import static com.felzan.coffeeshop.adapters.web.ConstantsController.CART;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.felzan.coffeeshop.adapters.web.cart.requestbody.CartRequest;
import com.felzan.coffeeshop.application.models.Order;
import com.felzan.coffeeshop.application.ports.in.cart.CartIn;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RequestMapping(value = CART, produces = APPLICATION_JSON_VALUE)
public class CartController {

  CartIn cartIn;

  @GetMapping(value = "")
  public ResponseEntity<Order> getLastCartByUser() {
    return ResponseEntity.ok(cartIn.findLast());
  }

  @PostMapping(value = "", consumes = APPLICATION_JSON_VALUE)
  public ResponseEntity<Long> create(@RequestBody CartRequest cartRequest) {
    var cart = cartIn.save(cartRequest.toDTO());
    return ResponseEntity.status(HttpStatus.CREATED).body(cart.getId());
  }

  @PutMapping(value = "{cartId}", consumes = APPLICATION_JSON_VALUE)
  public ResponseEntity<?> replace(@PathVariable Long cartId,
      @RequestBody CartRequest cartRequest) {
    var cartDTO = cartRequest.toDTO();
    cartDTO.setId(cartId);
    cartIn.replace(cartDTO);
    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

}
