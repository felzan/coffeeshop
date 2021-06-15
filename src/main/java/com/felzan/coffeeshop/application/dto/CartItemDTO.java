package com.felzan.coffeeshop.application.dto;


import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.application.models.CartItem;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(level = PRIVATE)
public class CartItemDTO extends ProductDTO {

  Integer quantity;

  public CartItem toCartItem() {
    return CartItem.builder()
        .quantity(getQuantity())
        .name(getName())
        .description(getDescription())
        .available(isAvailable())
        .image(getImage())
        .price(getPrice())
        .sku(getSku())
        .build();
  }
}
