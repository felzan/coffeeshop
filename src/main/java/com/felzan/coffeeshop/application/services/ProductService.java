package com.felzan.coffeeshop.application.services;

import com.felzan.coffeeshop.application.dto.ProductDTO;
import com.felzan.coffeeshop.application.ports.in.CreateProductIn;
import com.felzan.coffeeshop.application.ports.out.SaveProduct;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class ProductService implements CreateProductIn {

    SaveProduct saveProduct;

    @Override
    public void create(ProductDTO dto) {
        saveProduct.save(dto.toProduct());
    }
}
