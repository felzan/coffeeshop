package com.felzan.coffeeshop.application.ports.in.product;

import com.felzan.coffeeshop.application.dto.ProductDTO;

public interface UpdateProductIn {

    void update(Long productId, ProductDTO productDTO);
}
