package com.felzan.coffeeshop.application.models;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class Order extends BaseModel {

  String sessionId;
  Long userId;
  List<OrderItem> itemList;
  String status;
  PaymentInfo paymentInfo;
}