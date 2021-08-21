package com.felzan.coffeeshop.adapters.mysql.cartitem;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.adapters.mysql.BaseEntity;
import com.felzan.coffeeshop.adapters.mysql.order.OrderEntity;
import com.felzan.coffeeshop.application.models.OrderItem;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "orderItem")
@Entity(name = "orderItem")
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class OrderItemEntity extends BaseEntity {

  Long productId;
  Integer quantity;
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "order_id", updatable = false)
  OrderEntity order;

  String name;
  String description;
  boolean available;
  String image;
  Integer price;
  String sku;

  public OrderItemEntity(OrderItem orderItem) {
    setName(orderItem.getName());
    setDescription(orderItem.getDescription());
    setAvailable(orderItem.isAvailable());
    setImage(orderItem.getImage());
    setPrice(orderItem.getPrice());
    setSku(orderItem.getSku());

    setProductId(orderItem.getProductId());
    setQuantity(orderItem.getQuantity());
  }

  public OrderItem toModel() {
    return OrderItem.builder()
        .id(getId())
        .createdAt(getCreatedAt())
        .updatedAt(getUpdatedAt())

        .name(getName())
        .description(getDescription())
        .available(isAvailable())
        .image(getImage())
        .price(getPrice())
        .sku(getSku())

        .productId(getProductId())
        .quantity(getQuantity())
        .build();
  }
}
