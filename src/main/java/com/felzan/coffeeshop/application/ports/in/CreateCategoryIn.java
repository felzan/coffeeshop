package com.felzan.coffeeshop.application.ports.in;

import com.felzan.coffeeshop.application.dto.CategoryDTO;

public interface CreateCategoryIn {

    void create(CategoryDTO dto);
}
