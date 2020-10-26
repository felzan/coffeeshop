package com.felzan.coffeeshop.application.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product extends BaseModel {

    String name;
    String description;
    boolean available;
    String image;
    Integer price;
    String sku;
}
