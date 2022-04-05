package com.felzan.coffeeshop.application.services;

import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.application.dto.CartDTO;
import com.felzan.coffeeshop.application.exceptions.NotFoundException;
import com.felzan.coffeeshop.application.models.Order;
import com.felzan.coffeeshop.application.models.OrderItem;
import com.felzan.coffeeshop.application.models.Product;
import com.felzan.coffeeshop.application.models.User;
import com.felzan.coffeeshop.application.ports.in.cart.CartIn;
import com.felzan.coffeeshop.application.ports.out.ClearCart;
import com.felzan.coffeeshop.application.ports.out.FindCart;
import com.felzan.coffeeshop.application.ports.out.FindProduct;
import com.felzan.coffeeshop.application.ports.out.FindUser;
import com.felzan.coffeeshop.application.ports.out.SaveCart;
import java.math.BigDecimal;
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
  FindUser findUser;
  FindProduct findProduct;
  PaymentService payment;

  @Override
  public Order save(CartDTO dto) {
    List<Long> productsIdsList = new ArrayList<>(dto.getCartItems().keySet());
    List<Product> products = findProduct.FindByIds(productsIdsList);
    if (products.size() < productsIdsList.size()) {
      throw new NotFoundException();
    }

    List<OrderItem> orderItems = products.stream()
        .map(product ->
            OrderItem.builder()
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

    Order order = dto.toCart();
    order.setItemList(orderItems);

    if (isCheckout(dto)) {
      String currentStatus;
      int total = order.getItemList().stream().mapToInt(Product::getPrice).reduce(0,
          Integer::sum);

      User user = findUser.findById(dto.getUserId());
      var paymentInfo = dto.getPaymentInfo();
      paymentInfo.setUserReference(user.getUsername());
      paymentInfo.setValue(new BigDecimal(total));
      if (payment.execute(paymentInfo.toModel())) {
        currentStatus = "PAID";
      } else {
        currentStatus = "PAYMENT_FAILED";
      }
      order.setStatus(currentStatus);
    }
    return saveCart.save(order);
  }

  @Override
  public Order replace(CartDTO dto) {
    clearCart.clear(dto.getId());
    return save(dto);
  }

  private boolean isCheckout(CartDTO dto) {
//        TODO: get last status and check
    return dto.getStatus() != null
        && dto.getStatus().equals("CHECKOUT")
        && !dto.getStatus().equals("PAID");
  }

  @Override
  public Order findLast() {
    return findCart.findLast();
  }

  @Override
  public List<Order> findAll() {
    return findCart.findAll();
  }

  @Override
  public Order findOne(Long id) {
    return findCart.findOne(id);
  }

  @Override
  public void updateStatus(Long id, String status) {
    Order order = findCart.findOne(id);
    order.setStatus(status);
    saveCart.save(order);
  }
}
