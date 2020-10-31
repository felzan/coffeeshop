package com.felzan.coffeeshop.application.ports.out;

import com.felzan.coffeeshop.application.models.Category;
import com.felzan.coffeeshop.application.ports.in.category.FindCategoryCriteria;

import java.util.List;
import java.util.Optional;

public interface FindCategory {

    List<Category> find(FindCategoryCriteria criteria);

    Optional<Category> findById(Long categoryId);
}
