package com.felzan.coffeeshop.adapters.mysql.cartitem;

import com.felzan.coffeeshop.adapters.mysql.BaseEntity;
import com.felzan.coffeeshop.adapters.mysql.cart.CartEntity;
import com.felzan.coffeeshop.application.models.CartItem;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cartItem")
@Entity(name = "cartItem")
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class CartItemEntity extends BaseEntity {

    Integer quantity;
    @ManyToOne(fetch = LAZY, cascade = {PERSIST})
    @JoinColumn(name = "cart_id", updatable = false)
    CartEntity cart;

    String name;
    String description;
    boolean available;
    String image;
    Integer price;
    String sku;

    public CartItemEntity(CartItem cartItem) {
        setId(cartItem.getId());
        setName(cartItem.getName());
        setDescription(cartItem.getDescription());
        setAvailable(cartItem.isAvailable());
        setImage(cartItem.getImage());
        setPrice(cartItem.getPrice());
        setSku(cartItem.getSku());

        setQuantity(cartItem.getQuantity());
    }

    public CartItem toCartItem() {
        return CartItem.builder()
                .id(getId())
                .name(getName())
                .description(getDescription())
                .available(isAvailable())
                .image(getImage())
                .price(getPrice())
                .sku(getSku())
                .build();
    }
}
