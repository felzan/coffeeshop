package com.felzan.coffeeshop.adapters.mysql.product;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.adapters.mysql.BaseEntity;
import com.felzan.coffeeshop.adapters.mysql.category.CategoryEntity;
import com.felzan.coffeeshop.adapters.mysql.merchant.MerchantEntity;
import com.felzan.coffeeshop.application.models.Product;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
@Entity(name = "product")
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class ProductEntity extends BaseEntity {

  String name;
  String description;
  boolean available;
  String image;
  Integer price;
  String sku;
  @ManyToMany(targetEntity = CategoryEntity.class, mappedBy = "products", fetch = LAZY)
  List<CategoryEntity> categories;
  @ManyToOne
  @JoinColumn(name = "merchant_id")
  MerchantEntity merchant;

  public ProductEntity(Product product) {
    setId(product.getId());
    setName(product.getName());
    setDescription(product.getDescription());
    setAvailable(product.isAvailable());
    setImage(product.getImage());
    setPrice(product.getPrice());
    setSku(product.getSku());
  }

  public Product toModel() {
    return Product.builder()
        .id(getId())
        .name(getName())
        .description(getDescription())
        .available(isAvailable())
        .image(getImage())
        .price(getPrice())
        .sku(getSku())
        .build();
  }
}
