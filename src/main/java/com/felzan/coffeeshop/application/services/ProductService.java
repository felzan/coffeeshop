package com.felzan.coffeeshop.application.services;

import com.felzan.coffeeshop.application.dto.ProductDTO;
import com.felzan.coffeeshop.application.models.Product;
import com.felzan.coffeeshop.application.ports.in.product.CreateProductIn;
import com.felzan.coffeeshop.application.ports.in.product.DeleteProductIn;
import com.felzan.coffeeshop.application.ports.in.product.FindProductIn;
import com.felzan.coffeeshop.application.ports.in.product.UpdateProductIn;
import com.felzan.coffeeshop.application.ports.out.DeleteProduct;
import com.felzan.coffeeshop.application.ports.out.FindProduct;
import com.felzan.coffeeshop.application.ports.out.SaveProduct;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class ProductService implements CreateProductIn, DeleteProductIn, FindProductIn, UpdateProductIn {

    SaveProduct saveProduct;
    DeleteProduct deleteProduct;
    FindProduct findProduct;

    @Override
    public void create(ProductDTO dto) {
        saveProduct.save(dto.toProduct());
    }

    @Override
    public void delete(Long productId) {
        deleteProduct.delete(productId);
    }

    @Override
    public List<Product> find() {
        return findProduct.findAll();
    }

    @Override
    public Product findById(Long productId) {
        return findProduct.findById(productId).orElseThrow(RuntimeException::new);
    }

    @Override
    public void update(Long productId, ProductDTO productDTO) {
        Product product = productDTO.toProduct();
        product.setId(productId);
        saveProduct.save(product);
    }

    public List<Product> findAllByIds(List<Long> ids) {
        return findProduct.FindByIds(ids);
    }

}
