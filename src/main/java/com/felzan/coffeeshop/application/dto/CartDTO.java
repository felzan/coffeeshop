package com.felzan.coffeeshop.application.dto;


import com.felzan.coffeeshop.application.models.Cart;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(level = PRIVATE)
public class CartDTO extends BaseDTO {

    String sessionId;
    Long userId;
    Map<Long, Integer> cartItems;


//    public CartDTO(Product product) {
//    }

    public Cart toCart() {
        return Cart.builder()
                .id(getId())
                .sessionId(getSessionId())
                .userId(getUserId())
                .build();
    }
}
