package com.felzan.coffeeshop.application.dto;


import com.felzan.coffeeshop.application.models.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDTO {

    String name;
    String description;
    boolean available;
    String image;
    Integer price;
    String sku;

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
