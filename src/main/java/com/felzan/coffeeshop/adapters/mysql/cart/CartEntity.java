package com.felzan.coffeeshop.adapters.mysql.cart;

import com.felzan.coffeeshop.adapters.mysql.BaseEntity;
import com.felzan.coffeeshop.adapters.mysql.cartitem.CartItemEntity;
import com.felzan.coffeeshop.adapters.mysql.user.UserEntity;
import com.felzan.coffeeshop.application.models.Cart;
import com.felzan.coffeeshop.application.models.CartItem;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart")
@Entity(name = "cart")
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class CartEntity extends BaseEntity {

    String sessionId;
    @ManyToOne(fetch = LAZY)
    UserEntity user;
    @OneToMany(targetEntity = CartItemEntity.class, mappedBy = "cart", fetch = EAGER)
    List<CartItemEntity> cartItems;

    public CartEntity(Cart cart) {
        List<CartItemEntity> items = cart.getItemList().stream()
                .map(CartItemEntity::new)
                .collect(Collectors.toList());

        setId(cart.getId());

        setCartItems(items);
        setSessionId(cart.getSessionId());
    }

    public Cart toCart() {
        List<CartItem> items = getCartItems().stream()
                .map(CartItemEntity::toCartItem)
                .collect(Collectors.toList());

        return Cart.builder()
                .id(getId())
                .createdAt(getCreatedAt())
                .updatedAt(getUpdatedAt())

                .sessionId(getSessionId())
                .userId(getUser().getId())
                .itemList(items)
                .build();
    }
}
