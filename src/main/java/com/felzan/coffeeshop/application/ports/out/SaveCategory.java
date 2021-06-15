package com.felzan.coffeeshop.application.ports.out;

import com.felzan.coffeeshop.application.models.Category;

public interface SaveCategory {

  void save(Category category);
}
