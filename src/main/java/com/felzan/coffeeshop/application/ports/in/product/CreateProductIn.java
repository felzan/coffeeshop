package com.felzan.coffeeshop.application.ports.in.product;

import com.felzan.coffeeshop.application.dto.ProductDTO;
import com.felzan.coffeeshop.application.models.Product;

public interface CreateProductIn {

  Product create(ProductDTO dto);
}
