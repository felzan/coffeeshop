package com.felzan.coffeeshop.application.services;

import com.felzan.coffeeshop.adapters.web.admin.requestbody.CreateCategoryRequest;
import com.felzan.coffeeshop.application.ports.in.CreateCategory;
import com.felzan.coffeeshop.application.ports.in.UpdateCategory;
import com.felzan.coffeeshop.application.ports.out.SaveCategory;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class CategoryService implements CreateCategory, UpdateCategory {

    SaveCategory saveCategory;

    @Override
    public void create(CreateCategoryRequest category) {
        saveCategory.save(category);
    }

    @Override
    public void update(Long id, CreateCategoryRequest category) {
        saveCategory.save(id, category);
    }
}
