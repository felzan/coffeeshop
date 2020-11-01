package com.felzan.coffeeshop.adapters.mysql.category;

import com.felzan.coffeeshop.application.models.Category;
import com.felzan.coffeeshop.application.ports.in.category.FindCategoryCriteria;
import com.felzan.coffeeshop.application.ports.out.DeleteCategory;
import com.felzan.coffeeshop.application.ports.out.FindCategory;
import com.felzan.coffeeshop.application.ports.out.SaveCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryDAO implements SaveCategory, FindCategory, DeleteCategory {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> find(FindCategoryCriteria criteria) {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        return categoryEntityList.stream()
                .map(CategoryEntity::toCategory)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Category> findById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .map(CategoryEntity::toCategory);
    }

    @Override
    public void save(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity(category);

        categoryRepository.save(categoryEntity);
    }

    @Override
    public void delete(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
