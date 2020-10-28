package com.felzan.coffeeshop.adapters.mysql.category;

import com.felzan.coffeeshop.application.models.Category;
import com.felzan.coffeeshop.application.ports.in.category.FindCategory;
import com.felzan.coffeeshop.application.ports.in.category.FindCategoryCriteria;
import com.felzan.coffeeshop.application.ports.out.DeleteCategory;
import com.felzan.coffeeshop.application.ports.out.SaveCategory;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class CategoryDAO implements SaveCategory, FindCategory, DeleteCategory {

    CategoryRepository categoryRepository;

    @Override
    public Iterable<CategoryEntity> find(FindCategoryCriteria criteria) {
        return categoryRepository.findAll();
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
