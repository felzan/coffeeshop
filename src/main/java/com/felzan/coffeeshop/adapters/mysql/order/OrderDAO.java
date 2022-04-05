package com.felzan.coffeeshop.adapters.mysql.order;

import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.adapters.mysql.cartitem.CartItemRepository;
import com.felzan.coffeeshop.adapters.mysql.user.UserEntity;
import com.felzan.coffeeshop.adapters.mysql.user.UserRepository;
import com.felzan.coffeeshop.application.exceptions.NotFoundException;
import com.felzan.coffeeshop.application.models.Order;
import com.felzan.coffeeshop.application.ports.out.ClearCart;
import com.felzan.coffeeshop.application.ports.out.FindCart;
import com.felzan.coffeeshop.application.ports.out.SaveCart;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class OrderDAO implements ClearCart, SaveCart, FindCart {

  OrderRepository orderRepository;
  CartItemRepository cartItemRepository;
  UserRepository userRepository;

  @Override
  public Order save(Order order) {
    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    UserEntity userEntity =
        userRepository.findByUsername(currentUser.getUsername()).orElseThrow(RuntimeException::new);
    OrderEntity entity = new OrderEntity(order);
    entity.getOrderItems().forEach(cartItemEntity -> cartItemEntity.setOrder(entity));
    entity.setUser(userEntity);

    Order savedOrder = orderRepository.save(entity).toModel();
//        cartItemRepository.saveAll(entity.getCartItems());
    return savedOrder;
  }

  @Override
  public Order findLast() {
    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    UserEntity userEntity =
        userRepository.findByUsername(currentUser.getUsername()).orElseThrow(RuntimeException::new);
    // TODO: verify user has cart
    return orderRepository.findTopByUser_Id(userEntity.getId()).toModel();
  }

  @Override
  public List<Order> findAll() {
    return orderRepository.findAll().stream()
        .map(OrderEntity::toModel)
        .collect(Collectors.toList());
  }

  @Override
  public Order findOne(Long id) {
    return orderRepository.findById(id).orElseThrow(NotFoundException::new).toModel();
  }

  @Override
  public void clear(Long cartId) {
    OrderEntity orderEntity = orderRepository.findById(cartId).orElseThrow();
    cartItemRepository.deleteAll(orderEntity.getOrderItems());
//        orderEntity.getCartItems().forEach(item -> cartItemRepository.deleteById(item.getId()));
  }
}
