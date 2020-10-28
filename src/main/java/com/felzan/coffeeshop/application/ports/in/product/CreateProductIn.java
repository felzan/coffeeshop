package com.felzan.coffeeshop.application.ports.in.product;

import com.felzan.coffeeshop.application.dto.ProductDTO;

public interface CreateProductIn {

    void create(ProductDTO dto);
}
