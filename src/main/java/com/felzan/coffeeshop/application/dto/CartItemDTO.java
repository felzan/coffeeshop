package com.felzan.coffeeshop.application.dto;


import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.application.models.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(level = PRIVATE)
public class CartItemDTO extends ProductDTO {

  Integer quantity;

  public OrderItem toCartItem() {
    return OrderItem.builder()
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
