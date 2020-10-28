package com.felzan.coffeeshop.adapters.mysql.product;

import com.felzan.coffeeshop.application.models.Product;
import com.felzan.coffeeshop.application.ports.out.DeleteProduct;
import com.felzan.coffeeshop.application.ports.out.SaveProduct;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class ProductDAO implements SaveProduct, DeleteProduct {

    ProductRepository productRepository;

    @Override
    public void save(Product product) {
        ProductEntity productEntity = new ProductEntity(product);
        productRepository.save(productEntity);
    }

    @Override
    public void delete(Long productId) {
        productRepository.deleteById(productId);
    }
}
