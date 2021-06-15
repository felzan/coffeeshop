package com.felzan.coffeeshop.application.services.fixture;

import com.felzan.coffeeshop.application.models.Category;
import java.util.Collections;
import java.util.List;

public class CategoryFixture {

  public static Category categoryFixture() {
    return Category.builder()
        .id(99L)
        .name("Category")
        .build();
  }

  public static List<Category> categoryListFixture() {
    return Collections.singletonList(categoryFixture());
  }
}
