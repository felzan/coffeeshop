package com.felzan.coffeeshop.application.services;

import com.felzan.coffeeshop.application.dto.CategoryDTO;
import com.felzan.coffeeshop.application.models.Category;
import com.felzan.coffeeshop.application.models.Product;
import com.felzan.coffeeshop.application.ports.in.CreateCategory;
import com.felzan.coffeeshop.application.ports.in.UpdateCategory;
import com.felzan.coffeeshop.application.ports.out.SaveCategory;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class CategoryService implements CreateCategory, UpdateCategory {

    SaveCategory saveCategory;

    @Override
    public void create(CategoryDTO dto) {
        // TODO get products by ids
        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        product.setId(1L);
        productList.add(product);
        Category category = dto.toCategory();
        category.setProducts(productList);
        // TODO create status
        String status = "CREATED";
        category.setStatus(status);
        saveCategory.save(category);
    }

    @Override
    public void update(Long id, CategoryDTO dto) {
        Category category = dto.toCategory();
        category.setId(id);
        saveCategory.save(category);
    }
}
