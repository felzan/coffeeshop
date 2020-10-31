package com.felzan.coffeeshop.application.dto;


import com.felzan.coffeeshop.application.models.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class ProductDTO extends BaseDTO {

    private String name;
    private String description;
    private boolean available;
    private String image;
    private Integer price;
    private String sku;

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
