package com.felzan.coffeeshop.application.dto;


import com.felzan.coffeeshop.application.models.Category;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryDTO extends BaseDTO {

    String name;
    String description;
    String image;
    String status;
    boolean visible;
    List<Long> productsIds;

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
