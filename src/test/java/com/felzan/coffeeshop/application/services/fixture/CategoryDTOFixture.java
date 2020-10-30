package com.felzan.coffeeshop.application.services.fixture;

import com.felzan.coffeeshop.application.dto.CategoryDTO;

import java.util.Collections;
import java.util.List;

public class CategoryDTOFixture {

    public static CategoryDTO categoryDTOFixture() {
        return CategoryDTO.builder()
                .id(99L)
                .name("Category")
                .productsIds(List.of(1L))
                .build();
    }

    public static List<CategoryDTO> categoryDTOListFixture() {
        return Collections.singletonList(categoryDTOFixture());
    }
}
