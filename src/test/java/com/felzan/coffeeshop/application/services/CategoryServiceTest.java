package com.felzan.coffeeshop.application.services;

import com.felzan.coffeeshop.application.dto.CategoryDTO;
import com.felzan.coffeeshop.application.models.Category;
import com.felzan.coffeeshop.application.ports.out.SaveCategory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
    @InjectMocks
    private CategoryService categoryService;
    @Mock
    private SaveCategory saveCategory;

    @Test
    @DisplayName("Should create a category")
    void create() {
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .name("category")
                .description("description")
                .image("imageUrl")
                .visible(true)
                .build();

        categoryService.create(categoryDTO);

        verify(saveCategory, times(1)).save(any(Category.class));
    }

    @Test
    @Disabled("Not implemented yet")
    void createWithProductsIds() {
    }
}