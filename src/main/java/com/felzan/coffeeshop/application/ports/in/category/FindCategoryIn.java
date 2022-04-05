package com.felzan.coffeeshop.application.ports.in.category;

import com.felzan.coffeeshop.application.models.Category;
import java.util.List;

public interface FindCategoryIn {

  List<Category> find(FindCategoryCriteria criteria);

  Category findById(Long categoryId);
}
