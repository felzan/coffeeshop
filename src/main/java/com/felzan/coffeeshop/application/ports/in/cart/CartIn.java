package com.felzan.coffeeshop.application.ports.in.cart;

import com.felzan.coffeeshop.application.dto.CartDTO;
import com.felzan.coffeeshop.application.models.Cart;

public interface CartIn {

    void save(CartDTO dto);

    Cart findLast();
}
