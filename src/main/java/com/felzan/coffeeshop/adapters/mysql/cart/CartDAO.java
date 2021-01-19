package com.felzan.coffeeshop.adapters.mysql.cart;

import com.felzan.coffeeshop.adapters.mysql.cartitem.CartItemRepository;
import com.felzan.coffeeshop.adapters.mysql.user.UserEntity;
import com.felzan.coffeeshop.adapters.mysql.user.UserRepository;
import com.felzan.coffeeshop.application.exceptions.NotFoundException;
import com.felzan.coffeeshop.application.models.Cart;
import com.felzan.coffeeshop.application.ports.out.FindCart;
import com.felzan.coffeeshop.application.ports.out.SaveCart;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class CartDAO implements SaveCart, FindCart {

    CartRepository cartRepository;
    CartItemRepository cartItemRepository;
    UserRepository userRepository;

    @Override
    public void save(Cart cart) {
        CartEntity entity = new CartEntity(cart);
        entity.getCartItems().forEach(cartItemEntity -> cartItemEntity.setCart(entity));
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity userEntity = userRepository.findByUsername(currentUser.getUsername()).orElseThrow(RuntimeException::new);
        entity.setUser(userEntity);

        cartRepository.save(entity);
        cartItemRepository.saveAll(entity.getCartItems());
    }

    @Override
    public Cart findLast() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity userEntity = userRepository.findByUsername(currentUser.getUsername()).orElseThrow(RuntimeException::new);
        // TODO: verify user has cart
        return cartRepository.findTopByUser_Id(userEntity.getId()).toCart();
    }

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll().stream()
                .map(cartEntity -> cartEntity.toCart())
                .collect(Collectors.toList());
    }

    @Override
    public Cart findOne(Long id) {
        return cartRepository.findById(id).orElseThrow(NotFoundException::new).toCart();
    }
}
