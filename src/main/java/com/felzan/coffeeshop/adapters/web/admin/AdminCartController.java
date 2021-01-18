package com.felzan.coffeeshop.adapters.web.admin;

import com.felzan.coffeeshop.application.models.Cart;
import com.felzan.coffeeshop.application.ports.in.cart.CartIn;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<Cart>> getAll() {
        return ResponseEntity.ok(cartIn.findAll());
    }

}
