package com.felzan.coffeeshop.adapters.mysql.product;

import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.application.models.Product;
import com.felzan.coffeeshop.application.ports.out.DeleteProduct;
import com.felzan.coffeeshop.application.ports.out.FindProduct;
import com.felzan.coffeeshop.application.ports.out.SaveProduct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class ProductDAO implements FindProduct, DeleteProduct, SaveProduct {

  ProductRepository productRepository;

  @Override
  public List<Product> findAll() {
    List<ProductEntity> productEntityList = productRepository.findAll();
    return productEntityList.stream()
        .map(ProductEntity::toProduct)
        .collect(Collectors.toList());
  }

  @Override
  public Optional<Product> findById(Long productId) {
    return productRepository.findById(productId).map(ProductEntity::toProduct);
  }

  @Override
  public List<Product> FindByIds(List<Long> ids) {
    List<ProductEntity> productEntityList = productRepository.findAllById(ids);
    return productEntityList.stream()
        .map(ProductEntity::toProduct)
        .collect(Collectors.toList());
  }

  @Override
  public Product save(Product product) {
    ProductEntity productEntity = new ProductEntity(product);
    return productRepository.save(productEntity).toProduct();
  }

  @Override
  public void delete(Long productId) {
    productRepository.deleteById(productId);
  }

}
