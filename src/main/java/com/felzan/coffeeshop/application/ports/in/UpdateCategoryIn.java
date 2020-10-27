package com.felzan.coffeeshop.application.ports.in;

import com.felzan.coffeeshop.application.dto.CategoryDTO;

public interface UpdateCategoryIn {
    void update(Long categoryId, CategoryDTO category);
}
