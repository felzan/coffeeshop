package com.felzan.coffeeshop.application.models;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category extends BaseModel {

    String name;
    String description;
    String image;
    String status;
    boolean visible;
    List<Product> products;
}
