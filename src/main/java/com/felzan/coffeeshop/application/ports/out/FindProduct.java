package com.felzan.coffeeshop.application.ports.out;

import com.felzan.coffeeshop.application.models.Product;

import java.util.List;
import java.util.Optional;

public interface FindProduct {

    List<Product> FindByIds(List<Long> ids);

    List<Product> findAll();

    Optional<Product> findById(Long productId);
}
