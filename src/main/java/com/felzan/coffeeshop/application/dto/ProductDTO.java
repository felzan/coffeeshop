package com.felzan.coffeeshop.application.dto;


import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.application.models.Product;
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
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(level = PRIVATE)
public class ProductDTO extends BaseDTO {

  String name;
  String description;
  boolean available;
  String image;
  Integer price;
  String sku;

  public ProductDTO(Product product) {
    super(product.getId(), product.getCreatedAt(), product.getUpdatedAt());
    setName(product.getName());
    setDescription(product.getDescription());
    setAvailable(product.isAvailable());
    setImage(product.getImage());
    setPrice(product.getPrice());
    setSku(product.getSku());
  }

  public Product toProduct() {
    return Product.builder()
        .name(getName())
        .description(getDescription())
        .available(isAvailable())
        .image(getImage())
        .price(getPrice())
        .sku(getSku())
        .build();
  }
}
