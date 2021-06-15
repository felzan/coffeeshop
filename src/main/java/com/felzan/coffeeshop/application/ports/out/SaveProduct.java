package com.felzan.coffeeshop.application.ports.out;

import com.felzan.coffeeshop.application.models.Product;

public interface SaveProduct {

  Product save(Product product);
}
