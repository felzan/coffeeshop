package com.felzan.coffeeshop.application.services;

import com.felzan.coffeeshop.application.dto.CategoryDTO;
import com.felzan.coffeeshop.application.models.Category;
import com.felzan.coffeeshop.application.models.Product;
import com.felzan.coffeeshop.application.ports.in.category.*;
import com.felzan.coffeeshop.application.ports.out.DeleteCategory;
import com.felzan.coffeeshop.application.ports.out.FindCategory;
import com.felzan.coffeeshop.application.ports.out.SaveCategory;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class CategoryService implements CreateCategoryIn, FindCategoryIn, UpdateCategoryIn, DeleteCategoryIn {

    ProductService productService;

    SaveCategory saveCategory;
    FindCategory findCategory;
    DeleteCategory deleteCategory;

    @Override
    public void create(CategoryDTO dto) {
        List<Long> ids = dto.getProductsIds();
        Category category = dto.toCategory();

        List<Product> productList = productService.findAllByIds(ids);
        category.setProducts(productList);
        // TODO create status
        String status = "CREATED";
        category.setStatus(status);
        saveCategory.save(category);
    }

    @Override
    public void update(Long id, CategoryDTO dto) {
        List<Long> ids = dto.getProductsIds();
        Category category = dto.toCategory();
        category.setId(id);

        List<Product> productList = productService.findAllByIds(ids);
        category.setProducts(productList);
        saveCategory.save(category);
    }

    @Override
    public void delete(Long categoryId) {
        deleteCategory.delete(categoryId);
    }

    @Override
    public List<Category> find(FindCategoryCriteria criteria) {
        return findCategory.find(criteria);
    }

    @Override
    public Category findById(Long categoryId) {
        return findCategory.findById(categoryId).get();
    }
}
