package com.felzan.coffeeshop.adapters.mysql.cartitem;

import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.application.models.OrderItem;
import com.felzan.coffeeshop.application.ports.out.SaveCartItem;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class CartItemDAO implements SaveCartItem {

  CartItemRepository cartItemRepository;

  @Override
  public void save(OrderItem orderItem) {
    OrderItemEntity entity = new OrderItemEntity(orderItem);

    cartItemRepository.save(entity);
  }
}
