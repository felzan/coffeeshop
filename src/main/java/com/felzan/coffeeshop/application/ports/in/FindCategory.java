package com.felzan.coffeeshop.application.ports.in;

import com.felzan.coffeeshop.adapters.mysql.category.CategoryEntity;

public interface FindCategory {

    Iterable<CategoryEntity> find(FindCategoryCriteria criteria);
}
