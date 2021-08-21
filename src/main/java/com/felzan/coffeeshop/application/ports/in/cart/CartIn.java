package com.felzan.coffeeshop.application.ports.in.cart;

import com.felzan.coffeeshop.application.dto.CartDTO;
import com.felzan.coffeeshop.application.models.Order;
import java.util.List;

public interface CartIn {

  Order save(CartDTO dto);

  Order replace(CartDTO dto);

  Order findLast();

  List<Order> findAll();

  Order findOne(Long id);

  void updateStatus(Long id, String string);
}
