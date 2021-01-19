package com.felzan.coffeeshop.adapters.web.admin;

import com.felzan.coffeeshop.application.models.Cart;
import com.felzan.coffeeshop.application.ports.in.cart.CartIn;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.felzan.coffeeshop.adapters.web.ConstantsController.ADMIN_CART;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RequestMapping(value = ADMIN_CART, produces = APPLICATION_JSON_VALUE)
public class AdminCartController {

    CartIn cartIn;

    @GetMapping(value = "")
    public ResponseEntity<List<Cart>> findAll() {
        return ResponseEntity.ok(cartIn.findAll());
    }

    @GetMapping(value = "{cartId}")
    public ResponseEntity<Cart> findOne(@PathVariable Long cartId) {
        return ResponseEntity.ok(cartIn.findOne(cartId));
    }

    @PatchMapping(value = "{cartId}")
    public ResponseEntity<?> updateStatus(@PathVariable Long cartId,
                                          @RequestParam String status) {
        cartIn.updateStatus(cartId, status);
        return ResponseEntity.ok().build();
    }

}
