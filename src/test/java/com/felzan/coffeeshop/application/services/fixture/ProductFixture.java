package com.felzan.coffeeshop.application.services.fixture;

import com.felzan.coffeeshop.application.models.Product;

import java.util.Collections;
import java.util.List;

public class ProductFixture {

    public static Product productFixture() {
        return Product.builder()
                .id(1L)
                .name("Product")
                .build();
    }

    public static List<Product> productListFixture() {
        return Collections.singletonList(productFixture());
    }
}
