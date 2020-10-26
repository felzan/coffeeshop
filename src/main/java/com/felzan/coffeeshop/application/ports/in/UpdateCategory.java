package com.felzan.coffeeshop.application.ports.in;

import com.felzan.coffeeshop.adapters.web.admin.requestbody.CreateCategoryRequest;

public interface UpdateCategory {
    void update(Long categoryId, CreateCategoryRequest category);
}
