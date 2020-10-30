package com.felzan.coffeeshop.application.dto;


import com.felzan.coffeeshop.application.models.Product;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
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
