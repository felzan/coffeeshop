package com.felzan.coffeeshop.adapters.mysql.category;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import com.felzan.coffeeshop.adapters.mysql.BaseEntity;
import com.felzan.coffeeshop.adapters.mysql.product.ProductEntity;
import com.felzan.coffeeshop.application.models.Category;
import com.felzan.coffeeshop.application.models.Product;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
@Entity(name = "category")
@EqualsAndHashCode(callSuper = false)
public class CategoryEntity extends BaseEntity {

  private String name;
  private String description;
  private String image;
  private String status;
  private boolean visible;
  @ManyToMany(targetEntity = ProductEntity.class, cascade = ALL, fetch = LAZY)
  @JoinTable(name = "category_product",
      joinColumns = @JoinColumn(name = "category_id"),
      inverseJoinColumns = @JoinColumn(name = "product_id"))
  private List<ProductEntity> products;

  public CategoryEntity(Category category) {
    List<ProductEntity> products = category.getProducts().stream()
        .map(ProductEntity::new)
        .collect(Collectors.toList());

    setId(category.getId());
    setName(category.getName());
    setDescription(category.getDescription());
    setImage(category.getImage());
    setStatus(category.getStatus());
    setVisible(category.isVisible());
    setProducts(products);
  }

  public Category toCategory() {
    List<Product> products = getProducts().stream()
        .map(ProductEntity::toProduct)
        .collect(Collectors.toList());

    return Category.builder()
        .id(getId())
        .createdAt(getCreatedAt())
        .updatedAt(getUpdatedAt())
        .name(getName())
        .description(getDescription())
        .image(getImage())
        .status(getStatus())
        .visible(isVisible())
        .products(products)
        .build();
  }

}
