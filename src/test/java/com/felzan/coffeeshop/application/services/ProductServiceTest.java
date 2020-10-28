package com.felzan.coffeeshop.application.services;

import com.felzan.coffeeshop.application.dto.ProductDTO;
import com.felzan.coffeeshop.application.models.Product;
import com.felzan.coffeeshop.application.ports.out.DeleteProduct;
import com.felzan.coffeeshop.application.ports.out.SaveProduct;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @InjectMocks
    private ProductService productService;
    @Mock
    private SaveProduct saveProduct;
    @Mock
    private DeleteProduct deleteProduct;

    @Test
    @DisplayName("Should call save")
    void create() {
        ProductDTO productDTO = ProductDTO.builder()
                .name("Product")
                .build();

        productService.create(productDTO);

        verify(saveProduct).save(any(Product.class));
    }

    @Test
    @DisplayName("Should call save")
    void update() {
        Long productId = 1L;
        ProductDTO productDTO = ProductDTO.builder()
                .name("Product")
                .build();

        productService.update(productId, productDTO);

        verify(saveProduct).save(any(Product.class));
    }

    @Test
    @DisplayName("Should call delete")
    void delete() {
        Long productId = 1L;

        productService.delete(productId);

        verify(deleteProduct).delete(productId);
    }
}