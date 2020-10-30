package com.felzan.coffeeshop.adapters.web.admin.requestbody;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.felzan.coffeeshop.application.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateProductRequest {

    String name;
    String description;
    boolean available;
    String image;
    Integer price;
    String sku;

    public ProductDTO toDTO() {
        return ProductDTO.builder()
                .name(getName())
                .description(getDescription())
                .available(isAvailable())
                .image(getImage())
                .price(getPrice())
                .sku(getSku())
                .build();
    }
}
