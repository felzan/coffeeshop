package com.felzan.coffeeshop.application.dto;


import com.felzan.coffeeshop.application.models.Category;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class CategoryDTO extends BaseDTO {

    private String name;
    private String description;
    private String image;
    private String status;
    private boolean visible;
    private List<Long> productsIds;

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
