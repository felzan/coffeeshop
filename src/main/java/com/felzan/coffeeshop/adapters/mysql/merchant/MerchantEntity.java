package com.felzan.coffeeshop.adapters.mysql.merchant;

import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.adapters.mysql.BaseEntity;
import com.felzan.coffeeshop.adapters.mysql.category.CategoryEntity;
import com.felzan.coffeeshop.adapters.mysql.order.OrderEntity;
import com.felzan.coffeeshop.adapters.mysql.product.ProductEntity;
import com.felzan.coffeeshop.adapters.mysql.whitelabel.WhiteLabelEntity;
import com.felzan.coffeeshop.application.models.Merchant;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "merchant")
@Entity(name = "merchant")
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class MerchantEntity extends BaseEntity {

  String name;
  String description;
  String cnpj;
  Double latitude;
  Double longitude;
  @OneToMany(mappedBy = "merchant")
  List<ProductEntity> products;
  @OneToMany(mappedBy = "merchant")
  List<CategoryEntity> categories;
  @OneToMany(mappedBy = "merchant")
  List<OrderEntity> orders;
  @OneToMany(mappedBy = "merchant")
  List<MerchantWorkingHourEntity> workingHours;
  @ManyToOne
  @JoinColumn(name = "whitelabel_id")
  WhiteLabelEntity whiteLabel;

  public MerchantEntity(Merchant model) {
    var productList = model.getProducts().stream()
        .map(ProductEntity::new)
        .collect(Collectors.toList());
    var categoryList = model.getCategories().stream()
        .map(CategoryEntity::new)
        .collect(Collectors.toList());
    var orderList = model.getOrders().stream()
        .map(OrderEntity::new)
        .collect(Collectors.toList());
    var workingHourList = model.getWorkingHours().stream()
        .map(MerchantWorkingHourEntity::new)
        .collect(Collectors.toList());

    setName(model.getName());
    setDescription(model.getDescription());
    setCnpj(model.getCnpj());
    setLatitude(model.getLatitude());
    setLongitude(model.getLongitude());
    setProducts(productList);
    setCategories(categoryList);
    setOrders(orderList);
    setWorkingHours(workingHourList);
  }

  public Merchant toModel() {
    var productsList = products.stream()
        .map(ProductEntity::toModel)
        .collect(Collectors.toList());
    var categoriesList = categories.stream()
        .map(CategoryEntity::toModel)
        .collect(Collectors.toList());
    var ordersList = orders.stream()
        .map(OrderEntity::toModel)
        .collect(Collectors.toList());
    var workingHoursList = workingHours.stream()
        .map(MerchantWorkingHourEntity::toModel)
        .collect(Collectors.toList());

    return Merchant.builder()
        .id(getId())
        .createdAt(getCreatedAt())
        .updatedAt(getUpdatedAt())
        .name(name)
        .description(description)
        .cnpj(cnpj)
        .latitude(latitude)
        .longitude(longitude)
        .products(productsList)
        .categories(categoriesList)
        .orders(ordersList)
        .workingHours(workingHoursList)
        .build();
  }

}
