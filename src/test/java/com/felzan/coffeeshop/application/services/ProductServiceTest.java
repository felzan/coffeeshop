package com.felzan.coffeeshop.application.services;

import static com.felzan.coffeeshop.application.services.fixture.ProductFixture.productFixture;
import static com.felzan.coffeeshop.application.services.fixture.ProductFixture.productListFixture;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.felzan.coffeeshop.application.dto.ProductDTO;
import com.felzan.coffeeshop.application.models.Product;
import com.felzan.coffeeshop.application.ports.out.DeleteProduct;
import com.felzan.coffeeshop.application.ports.out.FindProduct;
import com.felzan.coffeeshop.application.ports.out.SaveProduct;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  @InjectMocks
  private ProductService productService;
  @Mock
  private SaveProduct saveProduct;
  @Mock
  private DeleteProduct deleteProduct;
  @Mock
  private FindProduct findProduct;

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

  @Test
  @DisplayName("Should call findAll")
  void find() {
    when(findProduct.findAll())
        .thenReturn(productListFixture());

    productService.find();
  }

  @Test
  @DisplayName("Should call findById")
  void findById() {
    Long productId = 1L;
    when(findProduct.findById(productId))
        .thenReturn(Optional.of(productFixture()));

    productService.findById(productId);
  }

  @Test
  @DisplayName("Should throw RuntimeException when findById return nothing")
  void findByIdThrowingException() {
    Long productId = 1L;

    when(findProduct.findById(productId))
        .thenReturn(Optional.empty());

    assertThrows(RuntimeException.class, () -> productService.findById(productId));
  }

  @Test
  @DisplayName("Should return products")
  void findAllByIds() {
    List<Long> ids = List.of(1L);
    when(findProduct.FindByIds(ids))
        .thenReturn(productListFixture());

    productService.findAllByIds(ids);
  }
}