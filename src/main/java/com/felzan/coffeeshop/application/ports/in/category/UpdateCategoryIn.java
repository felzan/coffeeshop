package com.felzan.coffeeshop.application.ports.in.category;

import com.felzan.coffeeshop.application.dto.CategoryDTO;

public interface UpdateCategoryIn {
    void update(Long categoryId, CategoryDTO category);
}
