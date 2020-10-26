package com.felzan.coffeeshop.application.ports.in;

import com.felzan.coffeeshop.adapters.web.admin.requestbody.CreateCategoryRequest;

public interface CreateCategory {

    void create(CreateCategoryRequest category);
}
