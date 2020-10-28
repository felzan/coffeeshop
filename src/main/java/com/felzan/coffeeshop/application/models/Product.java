package com.felzan.coffeeshop.application.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import static lombok.AccessLevel.PRIVATE;

@Data
@SuperBuilder
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class Product extends BaseModel {

    String name;
    String description;
    boolean available;
    String image;
    Integer price;
    String sku;
}
