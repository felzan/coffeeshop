package com.felzan.coffeeshop.adapters.mysql.cartitem;

import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.application.models.CartItem;
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
  public void save(CartItem cartItem) {
    CartItemEntity entity = new CartItemEntity(cartItem);

    cartItemRepository.save(entity);
  }
}
