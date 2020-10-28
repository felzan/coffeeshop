package com.felzan.coffeeshop.application.services;

import com.felzan.coffeeshop.application.dto.CategoryDTO;
import com.felzan.coffeeshop.application.models.Category;
import com.felzan.coffeeshop.application.models.Product;
import com.felzan.coffeeshop.application.ports.in.category.CreateCategoryIn;
import com.felzan.coffeeshop.application.ports.in.category.DeleteCategoryIn;
import com.felzan.coffeeshop.application.ports.in.category.UpdateCategoryIn;
import com.felzan.coffeeshop.application.ports.out.DeleteCategory;
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
public class CategoryService implements CreateCategoryIn, UpdateCategoryIn, DeleteCategoryIn {

    SaveCategory saveCategory;
    DeleteCategory deleteCategory;

    @Override
    public void create(CategoryDTO dto) {
        // TODO get products by ids
        List<Product> productList = new ArrayList<>();
        Product product = Product.builder()
                .id(1L)
                .build();
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
        List<Product> productList = new ArrayList<>();
        Product product = Product.builder()
                .id(1L)
                .build();
        productList.add(product);
        Category category = dto.toCategory();
        category.setProducts(productList);
        category.setId(id);
        saveCategory.save(category);
    }

    @Override
    public void delete(Long categoryId) {
        deleteCategory.delete(categoryId);
    }
}
