package com.felzan.coffeeshop.adapters.mysql.order;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.adapters.mysql.BaseEntity;
import com.felzan.coffeeshop.adapters.mysql.cartitem.OrderItemEntity;
import com.felzan.coffeeshop.adapters.mysql.merchant.MerchantEntity;
import com.felzan.coffeeshop.adapters.mysql.user.UserEntity;
import com.felzan.coffeeshop.application.models.Order;
import com.felzan.coffeeshop.application.models.OrderItem;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`order`")
@Entity(name = "`order`")
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class OrderEntity extends BaseEntity {

  String sessionId;
  String status;
  @ManyToOne(fetch = LAZY, cascade = ALL)
  UserEntity user;
  @OneToMany(targetEntity = OrderItemEntity.class, mappedBy = "order", fetch = EAGER, cascade = ALL,
      orphanRemoval = true)
  List<OrderItemEntity> orderItems;
  @ManyToOne(cascade = ALL)
  @JoinColumn(name = "merchant_id")
  MerchantEntity merchant;

  public OrderEntity(Order order) {
    List<OrderItemEntity> items = order.getItemList().stream()
        .map(OrderItemEntity::new)
        .collect(Collectors.toList());

    setId(order.getId());

    setOrderItems(items);
    setSessionId(order.getSessionId());
    setStatus(order.getStatus());
  }

  public Order toModel() {
    List<OrderItem> items = getOrderItems().stream()
        .map(OrderItemEntity::toModel)
        .collect(Collectors.toList());

    return Order.builder()
        .id(getId())
        .createdAt(getCreatedAt())
        .updatedAt(getUpdatedAt())

        .sessionId(getSessionId())
        .status(getStatus())
        .userId(getUser().getId())
        .itemList(items)
        .build();
  }
}
