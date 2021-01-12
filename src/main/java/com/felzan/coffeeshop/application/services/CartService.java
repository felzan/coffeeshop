package com.felzan.coffeeshop.application.services;

import com.felzan.coffeeshop.application.dto.CartDTO;
import com.felzan.coffeeshop.application.exceptions.NotFoundException;
import com.felzan.coffeeshop.application.models.Cart;
import com.felzan.coffeeshop.application.models.CartItem;
import com.felzan.coffeeshop.application.models.Product;
import com.felzan.coffeeshop.application.ports.in.cart.CartIn;
import com.felzan.coffeeshop.application.ports.out.FindProduct;
import com.felzan.coffeeshop.application.ports.out.SaveCart;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class CartService implements CartIn {

    SaveCart saveCart;
    FindProduct findProduct;

    @Override
    public void save(CartDTO dto) {
        List<Long> productsIdsList = new ArrayList<>(dto.getCartItems().keySet());
        List<Product> products = findProduct.FindByIds(productsIdsList);
        if (products.size() < productsIdsList.size()) {
            throw new NotFoundException();
        }

        List<CartItem> cartItems = products.stream()
                .map(product ->
                        CartItem.builder()
                                .id(product.getId())
                                .createdAt(product.getCreatedAt())
                                .updatedAt(product.getUpdatedAt())
                                .name(product.getName())
                                .description(product.getDescription())
                                .available(product.isAvailable())
                                .image(product.getImage())
                                .price(product.getPrice())
                                .sku(product.getSku())
                                .quantity(dto.getCartItems().get(product.getId()))
                                .build()
                ).collect(Collectors.toList());

        Cart cart = dto.toCart();
        cart.setItemList(cartItems);
        saveCart.save(cart);
    }
}
