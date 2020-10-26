package com.felzan.coffeeshop.application.dto;


import com.felzan.coffeeshop.application.models.Category;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryDTO {

    String name;
    String description;
    String image;
    String status;
    boolean visible;
    List<Integer> productsIds;

    public Category toCategory() {
        return Category.builder()
                .name(getName())
                .description(getDescription())
                .image(getImage())
                .status(getStatus())
                .visible(isVisible())
                .build();
    }
}
