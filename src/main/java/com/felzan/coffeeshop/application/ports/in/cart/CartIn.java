package com.felzan.coffeeshop.application.ports.in.cart;

import com.felzan.coffeeshop.application.dto.CartDTO;

public interface CartIn {

    void save(CartDTO dto);
}
