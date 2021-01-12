package com.felzan.coffeeshop.adapters.web.cart;

import com.felzan.coffeeshop.adapters.web.cart.requestbody.CartRequest;
import com.felzan.coffeeshop.application.dto.CartDTO;
import com.felzan.coffeeshop.application.models.Cart;
import com.felzan.coffeeshop.application.ports.in.cart.CartIn;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.felzan.coffeeshop.adapters.web.ConstantsController.CART;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RequestMapping(value = CART, produces = APPLICATION_JSON_VALUE)
public class CartController {

    CartIn cartIn;

    @GetMapping(value = "")
    public ResponseEntity<Cart> getLastCartByUser() {
        return ResponseEntity.ok(cartIn.findLast());
    }

    @PostMapping(value = "", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody CartRequest cartRequest) {
        cartIn.save(cartRequest.toDTO());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "{cartId}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> replace(@PathVariable Long cartId,
                                     @RequestBody CartRequest cartRequest) {
        CartDTO cartDTO = cartRequest.toDTO();
        cartDTO.setId(cartId);
        cartIn.save(cartDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
