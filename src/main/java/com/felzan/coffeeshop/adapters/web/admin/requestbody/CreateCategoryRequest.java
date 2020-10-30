package com.felzan.coffeeshop.adapters.web.admin.requestbody;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.felzan.coffeeshop.application.dto.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateCategoryRequest {

    String name;
    String description;
    // receive image somehow then upload to S3
    String imageUrl;
    boolean visible;
    List<Long> productsIds;

    public CategoryDTO toCategoryDTO() {
        return CategoryDTO.builder()
                .name(getName())
                .description(getDescription())
                .image(getImageUrl())
                .visible(isVisible())
                .productsIds(getProductsIds())
                .build();
    }
}
