package com.felzan.coffeeshop.application.services;

import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.application.dto.CartDTO;
import com.felzan.coffeeshop.application.exceptions.NotFoundException;
import com.felzan.coffeeshop.application.models.Cart;
import com.felzan.coffeeshop.application.models.CartItem;
import com.felzan.coffeeshop.application.models.Product;
import com.felzan.coffeeshop.application.ports.in.cart.CartIn;
import com.felzan.coffeeshop.application.ports.out.ClearCart;
import com.felzan.coffeeshop.application.ports.out.FindCart;
import com.felzan.coffeeshop.application.ports.out.FindProduct;
import com.felzan.coffeeshop.application.ports.out.SaveCart;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class CartService implements CartIn {

  SaveCart saveCart;
  ClearCart clearCart;
  FindCart findCart;
  FindProduct findProduct;
  PaymentService payment;

  @Override
  public Cart save(CartDTO dto) {
    if (isCheckout(dto)) {
      String currentStatus;
      if (payment.execute()) {
        currentStatus = "PAID";
      } else {
        currentStatus = "PAYMENT_FAILED";
      }
      dto.setStatus(currentStatus);
    }
    List<Long> productsIdsList = new ArrayList<>(dto.getCartItems().keySet());
    List<Product> products = findProduct.FindByIds(productsIdsList);
    if (products.size() < productsIdsList.size()) {
      throw new NotFoundException();
    }

    List<CartItem> cartItems = products.stream()
        .map(product ->
            CartItem.builder()
                .productId(product.getId())
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
    return saveCart.save(cart);
  }

  @Override
  public Cart replace(CartDTO dto) {
    clearCart.clear(dto.getId());
    return save(dto);
  }

  private boolean isCheckout(CartDTO dto) {
//        TODO: get last status and check
//        if (dto.getStatus() != null && dto.getStatus().equals("PAID")) {
//            throw new CannotUpdateCartException();
//        }
    return dto.getStatus() != null
        && dto.getStatus().equals("CHECKOUT")
        && !dto.getStatus().equals("PAID");
  }

  @Override
  public Cart findLast() {
    return findCart.findLast();
  }

  @Override
  public List<Cart> findAll() {
    return findCart.findAll();
  }

  @Override
  public Cart findOne(Long id) {
    return findCart.findOne(id);
  }

  @Override
  public void updateStatus(Long id, String status) {
    Cart cart = findCart.findOne(id);
    cart.setStatus(status);
    saveCart.save(cart);
  }
}
