package com.felzan.coffeeshop.application.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@SuperBuilder
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class Category extends BaseModel {

    String name;
    String description;
    String image;
    String status;
    boolean visible;
    List<Product> products;
}
