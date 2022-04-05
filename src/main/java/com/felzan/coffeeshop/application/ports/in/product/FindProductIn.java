package com.felzan.coffeeshop.application.ports.in.product;

import com.felzan.coffeeshop.application.models.Product;
import java.util.List;

public interface FindProductIn {

  List<Product> find();

  Product findById(Long productId);
}
